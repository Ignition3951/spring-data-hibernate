package com.utk;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.utk.entity.Address;
import com.utk.entity.Bid;
import com.utk.entity.FetchTestData;
import com.utk.entity.Item;
import com.utk.entity.TestData;
import com.utk.entity.Users;

public class ReadOnly {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("ch10");

	private FetchTestData storeTestData() {

		EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		entityManager.getTransaction().begin();

		Long[] itemIds = new Long[3];
		Long[] userIds = new Long[3];

		Users johndoe = new Users("John Doe");
		Address johndoeAddress = new Address();
		johndoeAddress.setCity("Some City");
		johndoeAddress.setStreet("Some Street");
		johndoeAddress.setZipcode("1234");
		johndoe.setHomeAddress(johndoeAddress);
		entityManager.persist(johndoe);
		userIds[0] = johndoe.getId();

		Users janeroe = new Users("Jane Roe");
		janeroe.setHomeAddress(johndoeAddress);
		entityManager.persist(janeroe);
		userIds[1] = janeroe.getId();

		Users robertdoe = new Users("Robert Doe");
		robertdoe.setHomeAddress(johndoeAddress);
		entityManager.persist(robertdoe);
		userIds[2] = robertdoe.getId();

		Item item = new Item("Item One", LocalDateTime.now().plusDays(1), johndoe);
		entityManager.persist(item);
		itemIds[0] = item.getId();
		for (int i = 1; i <= 3; i++) {
			Bid bid = new Bid(item, robertdoe, new BigDecimal(9 + i));
			item.addBids(bid);
			entityManager.persist(bid);
		}

		item = new Item("Item Two", LocalDateTime.now().plusDays(1), johndoe);
		entityManager.persist(item);
		itemIds[1] = item.getId();
		for (int i = 1; i <= 1; i++) {
			Bid bid = new Bid(item, janeroe, new BigDecimal(2 + i));
			item.addBids(bid);
			entityManager.persist(bid);
		}

		item = new Item("Item Three", LocalDateTime.now().plusDays(2), janeroe);
		entityManager.persist(item);
		itemIds[2] = item.getId();

		entityManager.getTransaction().commit();
		entityManager.close();

		FetchTestData testData = new FetchTestData();
		testData.items = new TestData(itemIds);
		testData.users = new TestData(userIds);
		return testData;
	}

	@Test
	public void immutableEntity() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		FetchTestData testData = storeTestData();
		em.getTransaction().begin();

		Long ITEM_ID = testData.items.getFirstId();

		Item item = em.find(Item.class, ITEM_ID);

		for (Bid bid : item.getBids()) {
			bid.setAmount(new BigDecimal("99.99")); // This has no effect
		}
		em.flush();
		em.clear();

		item = em.find(Item.class, ITEM_ID);
		for (Bid bid : item.getBids()) {
			assertNotEquals("99.99", bid.getAmount().toString());
		}

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void selectiveReadOnly() throws Exception {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		FetchTestData testData = storeTestData();
		em.getTransaction().begin();

		Long ITEM_ID = testData.items.getFirstId();

		{
			em.unwrap(Session.class).setDefaultReadOnly(true);

			Item item = em.find(Item.class, ITEM_ID);
			item.setName("New Name");

			em.flush(); // No UPDATE
		}
		{
			em.clear();
			Item item = em.find(Item.class, ITEM_ID);
			assertNotEquals("New Name", item.getName());
		}
		{
			Item item = em.find(Item.class, ITEM_ID);

			em.unwrap(Session.class).setReadOnly(item, true);

			item.setName("New Name");

			em.flush(); // No UPDATE
		}
		{
			em.clear();
			Item item = em.find(Item.class, ITEM_ID);
			assertNotEquals("New Name", item.getName());
		}
		{
			org.hibernate.query.Query<Item> query = em.unwrap(Session.class).createQuery("select i from Item i",
					Item.class);

			query.setReadOnly(true).list();

			List<Item> result = query.list();

			for (Item item : result)
				item.setName("New Name");

			em.flush(); // No UPDATE
		}
		{
			List<Item> items = em.createQuery("select i from Item i", Item.class)
					.setHint(org.hibernate.annotations.QueryHints.READ_ONLY, Boolean.TRUE).getResultList();

			for (Item item : items)
				item.setName("New Name");
			em.flush(); // No UPDATE
		}
		{
			em.clear();
			Item item = em.find(Item.class, ITEM_ID);
			assertNotEquals("New Name", item.getName());
		}

		em.getTransaction().commit();
		em.close();
	}

}
