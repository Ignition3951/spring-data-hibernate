package com.utk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.utk.entity.Address;
import com.utk.entity.Item;
import com.utk.entity.Users;

public class SimpleTransitionTest {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ch10");

	private static EntityManagerFactory entityManagerFactoryReplicate = Persistence
			.createEntityManagerFactory("ch10_replicate");

	@Test
	public void makePersistent() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = new Item();
		item.setName("Some Item");
		entityManager.persist(item);

		Long ITEM_ID = item.getId();

		entityManager.getTransaction().commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		assertEquals("Some Item", entityManager.find(Item.class, ITEM_ID).getName());
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	public void retrievePersistent() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item someItem = new Item();
		someItem.setName("Some Item");
		entityManager.persist(someItem);
		entityManager.getTransaction().commit();
		entityManager.close();
		Long ITEM_ID = someItem.getId();

		{
			entityManager = entityManagerFactory.createEntityManager();// this returns the same persitence context and
																		// hence the same object is returned
			entityManager.getTransaction().begin();
			Item item = entityManager.find(Item.class, ITEM_ID);
			if (item != null)
				item.setName("NEW NAME");
			entityManager.getTransaction().commit();
			entityManager.close();
		}
		{
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();

			Item itemA = entityManager.find(Item.class, ITEM_ID); // hits the database to fetch the data
			Item itemB = entityManager.find(Item.class, ITEM_ID); // Repeatable read from persistence context as the
																	// data was already fetched

			assertTrue(itemA == itemB);
			assertTrue(itemA.equals(itemB));
			assertTrue(itemA.getId().equals(itemB.getId()));

			entityManager.getTransaction().commit(); // Flush: Dirty check and SQL UPDATE
			entityManager.close();
		}

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		assertEquals("NEW NAME", entityManager.find(Item.class, ITEM_ID).getName());
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@Test
	public void retrievePersistentReference() {
		EntityManager em = entityManagerFactory.createEntityManager(); // Application-managed
		em.getTransaction().begin();
		Item someItem = new Item();
		someItem.setName("Some Item");
		em.persist(someItem);
		em.getTransaction().commit();
		em.close();
		Long ITEM_ID = someItem.getId();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		/*
		 * If the persistence context already contains an <code>Item</code> with the
		 * given identifier, that <code>Item</code> instance is returned by
		 * <code>getReference()</code> without hitting the database. Furthermore, if
		 * <em>no</em> persistent instance with that identifier is currently managed, a
		 * hollow placeholder will be produced by Hibernate, a proxy. This means
		 * <code>getReference()</code> will not access the database, and it doesn't
		 * return <code>null</code>, unlike <code>find()</code>.
		 */
		Item item = em.getReference(Item.class, ITEM_ID);

		/*
		 * JPA offers <code>PersistenceUnitUtil</code> helper methods such as
		 * <code>isLoaded()</code> to detect if you are working with an uninitialized
		 * proxy.
		 */
		PersistenceUnitUtil persistenceUtil = entityManagerFactory.getPersistenceUnitUtil();
		assertFalse(persistenceUtil.isLoaded(item));

		/*
		 * As soon as you call any method such as <code>Item#getName()</code> on the
		 * proxy, a <code>SELECT</code> is executed to fully initialize the placeholder.
		 * The exception to this rule is a method that is a mapped database identifier
		 * getter method, such as <code>getId()</code>. A proxy might look like the real
		 * thing but it is only a placeholder carrying the identifier value of the
		 * entity instance it represents. If the database record doesn't exist anymore
		 * when the proxy is initialized, an <code>EntityNotFoundException</code> will
		 * be thrown.
		 */
		// assertEquals("Some Item", item.getName());
		/*
		 * Hibernate has a convenient static <code>initialize()</code> method, loading
		 * the proxy's data.
		 */
//         Hibernate.initialize(item);

		em.getTransaction().commit();
		em.close();

		/*
		 * After the persistence context is closed, <code>item</code> is in detached
		 * state. If you do not initialize the proxy while the persistence context is
		 * still open, you get a <code>LazyInitializationException</code> if you access
		 * the proxy. You can't load data on-demand once the persistence context is
		 * closed. The solution is simple: Load the data before you close the
		 * persistence context.
		 */
		assertThrows(LazyInitializationException.class, () -> item.getName());
	}

	@Test
	public void makeTransient() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Item someItem = new Item();
		someItem.setName("Some Item4");
		em.persist(someItem);
		em.getTransaction().commit();
		em.close();
		Long ITEM_ID = someItem.getId();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		/*
		 * If you call <code>find()</code>, Hibernate will execute a <code>SELECT</code>
		 * to load the <code>Item</code>. If you call <code>getReference()</code>,
		 * Hibernate will attempt to avoid the <code>SELECT</code> and return a proxy.
		 */
		Item item = em.find(Item.class, ITEM_ID);
		// Item item = em.getReference(Item.class, ITEM_ID);

		/*
		 * Calling <code>remove()</code> will queue the entity instance for deletion
		 * when the unit of work completes, it is now in <em>removed</em> state. If
		 * <code>remove()</code> is called on a proxy, Hibernate will execute a
		 * <code>SELECT</code> to load the data. An entity instance has to be fully
		 * initialized during life cycle transitions. You may have life cycle callback
		 * methods or an entity listener enabled (see <a
		 * href="#EventListenersInterceptors"/>), and the instance must pass through
		 * these interceptors to complete its full life cycle.
		 */
		em.remove(item);

		/*
		 * An entity in removed state is no longer in persistent state, this can be
		 * checked with the <code>contains()</code> operation.
		 */
		assertFalse(em.contains(item));

		/*
		 * You can make the removed instance persistent again, cancelling the deletion.
		 */
		// em.persist(item);

		// hibernate.use_identifier_rollback was enabled, it now looks like a transient
		// instance
//		assertNull(item.getId()); at this point of time there is item will be fetched

		/*
		 * When the transaction commits, Hibernate synchronizes the state transitions
		 * with the database and executes the SQL <code>DELETE</code>. The JVM garbage
		 * collector detects that the <code>item</code> is no longer referenced by
		 * anyone and finally deletes the last trace of the data.
		 */
		em.getTransaction().commit();
		em.close();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		item = em.find(Item.class, ITEM_ID);
		assertNull(item);
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void refresh() throws ExecutionException, InterruptedException {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Item someItem = new Item();
		someItem.setName("Refresh Item");
		em.persist(someItem);
		em.getTransaction().commit();
		em.close();
		Long ITEM_ID = someItem.getId();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Item item = em.find(Item.class, ITEM_ID);
		item.setName("Refrresh Name");

		// Someone updates this row in the database!
		Executors.newSingleThreadExecutor().submit(() -> {
			EntityManager em1 = entityManagerFactory.createEntityManager();
			try {
				em1.getTransaction().begin();

				Session session = em1.unwrap(Session.class);
				session.doWork(con -> {
					Item item1 = em1.find(Item.class, ITEM_ID);
					item1.setName("Concurrent Update Name");
					em1.persist(item1);
				});

				em1.getTransaction().commit();
				em1.close();

			} catch (Exception ex) {
				throw new RuntimeException("Concurrent operation failure: " + ex, ex);
			}
			return null;
		}).get();

		em.refresh(item);
		em.getTransaction().commit(); // Flush: Dirty check and SQL UPDATE

		em.refresh(item);
		em.close();
		assertEquals("Concurrent Update Name", item.getName());
	}

	@Test
	public void replicate() {
		Long ITEM_ID;
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Item someItem = new Item();
		someItem.setName("Replicate Item");
		em.persist(someItem);
		em.getTransaction().commit();
		em.close();
		ITEM_ID = someItem.getId();

		EntityManager emA = getDatabaseA().createEntityManager();
		emA.getTransaction().begin();
		Item item = emA.find(Item.class, ITEM_ID);
		emA.getTransaction().commit();

		EntityManager emB = getDatabaseB().createEntityManager();
		emB.getTransaction().begin();
		emB.unwrap(Session.class).replicate(item, org.hibernate.ReplicationMode.LATEST_VERSION);
		Item item1 = emB.find(Item.class, ITEM_ID);
		assertEquals("Replicate Item", item1.getName());
		emB.getTransaction().commit();

		emA.close();
		emB.close();
	}

	@Test
	public void flushModeType() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Item someItem = new Item();
		someItem.setName("Original Name");
		em.persist(someItem);
		em.getTransaction().commit();
		em.close();
		Long ITEM_ID = someItem.getId();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Item item = em.find(Item.class, ITEM_ID);
		item.setName("New Name");

		// Disable flushing before queries:
		/**
		 * With FlushModeType.COMMIT we disable flushing before queries, so we may see
		 * different data returned by the query than what we have in memory. The
		 * synchronization then occurs only when the transaction commits.
		 * 
		 */
		em.setFlushMode(javax.persistence.FlushModeType.COMMIT);// Usually, Hibernate recognizes that data has changed
																// in
																// memory and synchronizes these modifications with the
																// database before the
																// query. We can control this behaviour by using
																// setFlushMode()

		assertEquals("Original Name", em.createQuery("select i.name from Item i where i.id = :id", String.class)
				.setParameter("id", ITEM_ID).getSingleResult());

		em.getTransaction().commit(); // Flush!
		em.close();
	}

	@Test
	public void scopeOfIdentity() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Item someItem = new Item();
		someItem.setName("Some Item");
		em.persist(someItem);
		em.getTransaction().commit();
		em.close();
		Long ITEM_ID = someItem.getId();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Item a = em.find(Item.class, ITEM_ID);
		Item b = em.find(Item.class, ITEM_ID);
		assertTrue(a == b);
		assertTrue(a.equals(b));
		assertEquals(a.getId(), b.getId());

		em.getTransaction().commit();
		em.close();
		// Persistence Context is gone, 'a' and 'b' are now references to instances in
		// detached state!

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Item c = em.find(Item.class, ITEM_ID);
		assertTrue(a != c); // The 'a' reference is still detached!
		assertFalse(a.equals(c));
		assertEquals(a.getId(), c.getId());

		em.getTransaction().commit();
		em.close();

		/**
		 * Whenever we work with instances in a detached state and test them for
		 * equality (usually in hash-based collections), we need to supply our own
		 * implementation of the equals() and hashCode() methods for our mapped entity
		 * class.
		 * 
		 */
		Set<Item> allItems = new HashSet<>();
		allItems.add(a);
		allItems.add(b);
		allItems.add(c);
		assertEquals(2, allItems.size()); // That seems wrong and arbitrary!

	}

	@Test
	public void detach() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Users someUser = new Users("johndoe");
		Address address = new Address();
		address.setCity("Some City");
		address.setStreet("Some Street");
		address.setZipcode("1234");
		someUser.setHomeAddress(address);
		em.persist(someUser);
		em.getTransaction().commit();
		em.close();
		Long USER_ID = someUser.getId();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Users user = em.find(Users.class, USER_ID);
		em.detach(user);
		assertFalse(em.contains(user));

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void mergeDetached() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Users detachedUser = new Users("johndoe");
		Address address = new Address();
		address.setCity("Some City");
		address.setStreet("Some Street");
		address.setZipcode("1234");
		detachedUser.setHomeAddress(address);
		em.persist(detachedUser);
		em.getTransaction().commit();
		em.close();
		Long USER_ID = detachedUser.getId();

		detachedUser.setUsername("johndoe");

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Users mergedUser = em.merge(detachedUser);
		// Discard 'detachedUser' reference after merging!

		// The 'mergedUser' is in persistent state
		mergedUser.setUsername("doejohn");

		em.getTransaction().commit(); // UPDATE in database
		em.close();

		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Users user = em.find(Users.class, USER_ID);
		assertEquals(user.getUsername(), "doejohn");
		em.getTransaction().commit();
		em.close();
	}


	private EntityManagerFactory getDatabaseA() {
		return entityManagerFactory;
	}

	private EntityManagerFactory getDatabaseB() {
		return entityManagerFactoryReplicate;
	}

}
