package com.jason.hibernatedemo;

import java.io.Serializable;
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
	private static SessionFactory sessionFactory;
	static {
		Configuration configuration = new Configuration().configure();

		sessionFactory = configuration.buildSessionFactory();

	}

	/**
	 * Rigourous Test :-)
	 */
	public void testGetPerson() {

		Session session = sessionFactory.openSession();

		PersonDTO person = session.get(PersonDTO.class, 1);

		System.out.println(person.toString());

		session.close();

	}

	public void testGetAll() {

		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from PersonDTO");

		List<PersonDTO> datas = query.getResultList();

		for (PersonDTO personDTO : datas) {

			System.out.println(personDTO.toString());

		}

		session.close();

	}

	public void testAdd() {

		Session session = sessionFactory.openSession();

		PersonDTO dto = new PersonDTO("王五", "1000");

		Serializable i = session.save(dto);

		System.out.println(i + "");

		session.close();

	}
}
