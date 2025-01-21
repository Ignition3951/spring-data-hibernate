package com.utk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import com.utk.entity.Bid;
import com.utk.entity.FetchTestData;
import com.utk.entity.Item;
import com.utk.entity.TestData;
import com.utk.entity.User;

public class CartesianProduct {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ch12");

	private FetchTestData storeTestData() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Long[] itemIds = new Long[3];
		Long[] userIds = new Long[3];

		User johndoe = new User("johndoe");
		em.persist(johndoe);
		userIds[0] = johndoe.getId();

		User janeroe = new User("janeroe");
		em.persist(janeroe);
		userIds[1] = janeroe.getId();

		User robertdoe = new User("robertdoe");
		em.persist(robertdoe);
		userIds[2] = robertdoe.getId();

		Item item = new Item("Item One", LocalDate.now().plusDays(1), johndoe);
		item.addImages("foo.jpg");
		item.addImages("bar.jpg");
		item.addImages("baz.jpg");
		em.persist(item);
		itemIds[0] = item.getId();
		for (int i = 1; i <= 3; i++) {
			Bid bid = new Bid(item, new BigDecimal(9 + i));
			item.getBids().add(bid);
			em.persist(bid);
		}

		Item item1 = new Item("Item Two", LocalDate.now().plusDays(1), johndoe);
		item1.addImages("a.jpg");
		item1.addImages("b.jpg");
		em.persist(item1);
		itemIds[1] = item1.getId();
		for (int i = 1; i <= 1; i++) {
			Bid bid = new Bid(item1, new BigDecimal(2 + i));
			item1.getBids().add(bid);
			em.persist(bid);
		}

		Item item3 = new Item("Item Three", LocalDate.now().plusDays(2), janeroe);
		em.persist(item3);
		itemIds[2] = item3.getId();

		em.getTransaction().commit();
		em.close();

		FetchTestData testData = new FetchTestData();
		testData.items = new TestData(itemIds);
		testData.users = new TestData(userIds);
		return testData;
	}

	@Test
	public void fetchCollections() {
		FetchTestData testData = storeTestData();
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Long ITEM_ID = testData.items.getFirstId();

		Item item = em.find(Item.class, ITEM_ID);
		// select i.*, b.*, img.*
		// from ITEM i
		// left outer join BID b on b.ITEM_ID = i.ID
		// left outer join IMAGE img on img.ITEM_ID = i.ID
		// where i.ID = ?

		em.detach(item);
		
		// getting deleted as they are in detached state and hibernate recognizes that
		// and removes the record
		item.getImages().forEach(name -> {
			System.out.println("IMAGE NAME : " + name);
		});

		assertEquals(3, item.getImages().size());
		assertEquals(3, item.getBids().size());

		em.getTransaction().commit();
		em.close();
	}

}