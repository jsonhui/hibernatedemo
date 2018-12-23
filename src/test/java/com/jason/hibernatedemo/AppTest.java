package com.jason.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jason.hibernatedemo.bean.Author;
import com.jason.hibernatedemo.bean.PersonDTO;

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

	public void testGetPerson() {// 得到单个数据

		Session session = sessionFactory.openSession();

		PersonDTO person = session.get(PersonDTO.class, 1);

		System.out.println(person.toString());

		session.close();

	}

	public void testGetAllForAuthor() {// 获取所有作者数据

		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from Author");

		List<Author> datas = query.getResultList();

		for (Author author : datas) {

			System.out.println(author.toString());

		}

		session.close();

	}

	public void testGetAll() {// 获取所有数据

		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from PersonDTO");

		List<PersonDTO> datas = query.getResultList();

		for (PersonDTO personDTO : datas) {

			System.out.println(personDTO.toString());

		}

		session.close();

	}

	public void testAdd() {// 增加数据

		Session session = sessionFactory.openSession();

		PersonDTO dto = new PersonDTO(300, "王五", "1200");

		Transaction transaction = session.beginTransaction();// 当没有添加自增长的时候需要开启事务

		session.saveOrUpdate(dto);// Serializable i = session.save(dto);

		transaction.commit();// 事务提交// System.out.println(i + "");

		session.close();

	}

	public void testDelete() {// 删除数据

		Session session = sessionFactory.openSession();

		PersonDTO dto = new PersonDTO();

		dto.setId(300);

		Transaction transaction = session.beginTransaction();// 开启事务

		session.delete(dto);

		transaction.commit();// 事务提交

		session.close();

	}

	public void testUpdate() {// 修改数据

		Session session = sessionFactory.openSession();

		PersonDTO dto = new PersonDTO();

		dto.setId(3);

		dto.setName("王五");

		dto.setMoney("2000");

		Transaction transaction = session.beginTransaction();// 开启事务

		session.update(dto);

		transaction.commit();// 事务提交

		session.close();

	}
}
