package com.utk;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.utk.model.Message;

public class HelloWorldHibernateTest {

	public static void main(String[] args) {
		storeLoadMessage();
	}

	private static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure().addAnnotatedClass(Message.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public static void storeLoadMessage() {
		try (SessionFactory sessionFactory = createSessionFactory(); Session session = sessionFactory.openSession();) {
			session.beginTransaction();

			Message message = new Message();
			message.setText("Hello World!!!!!!!");

			session.persist(message);

			session.getTransaction().commit();

			session.beginTransaction();

			CriteriaQuery<Message> criteriaQuery = session.getCriteriaBuilder().createQuery(Message.class);
			criteriaQuery.from(Message.class);

			List<Message> messages = session.createQuery(criteriaQuery).getResultList();
			messages.get(messages.size() - 1).setText("Updated message to Hello world from HibernateJPA!!!!");

			session.getTransaction().commit();
		}
	}

}
