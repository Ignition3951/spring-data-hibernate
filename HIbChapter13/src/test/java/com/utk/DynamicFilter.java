package com.utk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.utk.entity.Category;
import com.utk.entity.Item;
import com.utk.entity.User;

public class DynamicFilter {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch13");

	class DynamicFilterTestData {
		TestData categories;
		TestData items;
		TestData users;
	}

	private DynamicFilterTestData storeTestData() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		DynamicFilterTestData testData = new DynamicFilterTestData();

		testData.users = new TestData(new Long[2]);
		User johndoe = new User("johndoe");
		em.persist(johndoe);
		testData.users.identifiers[0] = johndoe.getId();
		User janeroe = new User("janeroe", 100);
		em.persist(janeroe);
		testData.users.identifiers[1] = janeroe.getId();

		testData.categories = new TestData(new Long[2]);
		Category categoryOne = new Category("One");
		em.persist(categoryOne);
		testData.categories.identifiers[0] = categoryOne.getId();
		Category categoryTwo = new Category("Two");
		em.persist(categoryTwo);
		testData.categories.identifiers[1] = categoryTwo.getId();

		testData.items = new TestData(new Long[3]);
		Item itemFoo = new Item("Foo", categoryOne, johndoe);
		em.persist(itemFoo);
		testData.items.identifiers[0] = itemFoo.getId();
		Item itemBar = new Item("Bar", categoryOne, janeroe);
		em.persist(itemBar);
		testData.items.identifiers[1] = itemBar.getId();
		Item itemBaz = new Item("Baz", categoryTwo, janeroe);
		em.persist(itemBaz);
		testData.items.identifiers[2] = itemBaz.getId();

		em.getTransaction().commit();
		em.close();
		return testData;
	}

	@Test
	public void filterItems() {
		storeTestData();

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		{

			org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("limitByUserRanking");

			filter.setParameter("currentUserRanking", 0);

			{
				List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
				// select * from ITEM where 0 >=
				// (select u.RANKING from USERS u where u.ID = SELLER_ID)
				assertEquals(1, items.size());
			}
			em.clear();
			{
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Item> criteria = cb.createQuery(Item.class);
				criteria.select(criteria.from(Item.class));
				List<Item> items = em.createQuery(criteria).getResultList();
				// select * from ITEM where 0 >=
				// (select u.RANKING from USERS u where u.ID = SELLER_ID)
				assertEquals(1, items.size());
			}
			em.clear();

			filter.setParameter("currentUserRanking", 100);
			List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
			assertEquals(3, items.size());
		}
		em.clear();

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void filterCollection() {
		DynamicFilterTestData testData = storeTestData();

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Long CATEGORY_ID = testData.categories.getFirstId();
		{

			org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("limitByUserRanking");

			filter.setParameter("currentUserRanking", 0);
			Category category = em.find(Category.class, CATEGORY_ID);
			assertEquals(1, category.getItems().size());

			em.clear();

			filter.setParameter("currentUserRanking", 100);
			category = em.find(Category.class, CATEGORY_ID);
			assertEquals(2, category.getItems().size());
		}
		em.clear();

		em.getTransaction().commit();
		em.close();

	}

}