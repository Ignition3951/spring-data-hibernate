package com.utk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;

import com.utk.entity.Item;

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


	private EntityManagerFactory getDatabaseA() {
		return entityManagerFactory;
	}

	private EntityManagerFactory getDatabaseB() {
		return entityManagerFactoryReplicate;
	}

}
