package com.jason.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testGetPerson() {

		Configuration configuration = new Configuration().configure();

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		PersonDTO person = session.get(PersonDTO.class, 1);

		System.out.println(person.toString());

		session.close();

	}

	public void testGetAll() {

		Configuration configuration = new Configuration().configure();

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from PersonDTO");

		List<PersonDTO> datas = query.getResultList();

		for (PersonDTO personDTO : datas) {
			System.out.println(personDTO.toString());
		}

		session.close();

	}
}
