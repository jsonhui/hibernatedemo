package com.jason.hibernatedemo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jason.hibernatedemo.bean.Author;
import com.jason.hibernatedemo.bean.Book;
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

	public void testGetAuthor() {// 得到单个作者

		Session session = sessionFactory.openSession();

		Author author = session.get(Author.class, 1);

		System.out.println(author.getName());

		Set<Book> books = author.getBooks();

		for (Book book : books) {

			System.out.println(book);

		}

		session.close();

	}

	public void testGetAllForBook() {// 获取所有书数据

		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from Book");

		List<Book> datas = query.getResultList();

		for (Book book : datas) {

			System.out.println(book.toString());

		}

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
	
	
	public void testAddAuthor() {// 增加作者

		Session session = sessionFactory.openSession();

		Author author = new Author();

		author.setName("谷姐");
		
		Book book1 = new Book();
		
		book1.setName("梦里花落知多少");
		
		Book book2 = new Book();
		
		book2.setName("梦里梦外");
		
		Set<Book> books = new HashSet<Book>();
		
		books.add(book1);
		
		books.add(book2);
		
		author.setBooks(books);

		Transaction transaction = session.beginTransaction();// 开启事务

		session.save(author);

		transaction.commit();// 事务提交

		session.close();

	}
	
	
}
