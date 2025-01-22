package com.utk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.junit.jupiter.api.Test;

import com.utk.entity.AuditLogRecord;
import com.utk.entity.Item;
import com.utk.entity.User;
import com.utk.interceptor.AuditLogInterceptor;

public class AuditLogging {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch13");

	@Test
	public void writeAuditLog() {
		Long CURRENT_USER_ID;
		User currentUser1 = new User("johndoe");
		{
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			User currentUser = new User("johndoe");
			em.persist(currentUser);
			em.persist(currentUser1);
			em.getTransaction().commit();
			em.close();
			CURRENT_USER_ID = currentUser.getId();
		}

		EntityManager em = emf.createEntityManager();

		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);

		Session session = sessionFactory.withOptions().interceptor(new AuditLogInterceptor()).openSession();

		AuditLogInterceptor interceptor = (AuditLogInterceptor) ((SessionImplementor) session).getInterceptor();
		interceptor.setCurrentSession(session);
		interceptor.setCurrentUserId(CURRENT_USER_ID);

		session.beginTransaction();
		Item item0 = new Item("Foo");
		item0.setSeller(currentUser1);
		session.persist(item0);
		session.getTransaction().commit();

		em.getTransaction().begin();
		List<AuditLogRecord> logs = em.createQuery("select lr from AuditLogRecord lr", AuditLogRecord.class)
				.getResultList();
		assertEquals(1, logs.size());
		assertEquals("insert", logs.get(0).getMessage());
		assertEquals(Item.class, logs.get(0).getEntityClass());
		assertEquals(item0.getId(), logs.get(0).getEntityId());
		assertEquals(CURRENT_USER_ID, logs.get(0).getUserId());
		em.createQuery("delete AuditLogRecord").executeUpdate();
		em.getTransaction().commit();

		session.beginTransaction();
		Item item = session.find(Item.class, item0.getId());
		item.setName("Bar");
		session.persist(item);
		session.getTransaction().commit();
		session.close();

		em.getTransaction().begin();
		logs = em.createQuery("select lr from AuditLogRecord lr", AuditLogRecord.class).getResultList();
		assertEquals(1, logs.size());
		assertEquals("update", logs.get(0).getMessage());
		assertEquals(Item.class, logs.get(0).getEntityClass());
		assertEquals(item.getId(), logs.get(0).getEntityId());
		assertEquals(CURRENT_USER_ID, logs.get(0).getUserId());
		em.getTransaction().commit();
		em.close();

	}
}
