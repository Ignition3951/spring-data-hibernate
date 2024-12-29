package com.utk;

import java.util.List;

import com.utk.model.Message;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HelloWorldJPATest {

	public static void main(String[] args) {
		storeLoadMessages();

	}

	public static void storeLoadMessages() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ch02");
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();

			Message message = new Message();
			message.setText("Hello World!!!!!!!");
			entityManager.persist(message);

			entityManager.getTransaction().commit();

			entityManager.getTransaction().begin();

			List<Message> messages = entityManager.createQuery("select m from Message m", Message.class)
					.getResultList();

			messages.get(messages.size() - 1).setText("Updated message to Hello world from JPA!!!!");
			entityManager.getTransaction().commit();

			entityManager.close();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			entityManagerFactory.close();
		}
	}

}
