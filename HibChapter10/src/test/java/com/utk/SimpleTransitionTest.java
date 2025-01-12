package com.utk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

			Item itemA = entityManager.find(Item.class, ITEM_ID);
			Item itemB = entityManager.find(Item.class, ITEM_ID); // Repeatable read

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


	private EntityManagerFactory getDatabaseA() {
		return entityManagerFactory;
	}

	private EntityManagerFactory getDatabaseB() {
		return entityManagerFactoryReplicate;
	}

}
