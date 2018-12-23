package com.jason.hibernatedemo.bean;

public class Book {

	private Integer id;
	private String name;
	private Author author;

	public Book() {
	}

	public Book(Integer id, String name, Author author) {
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + "]";
	}

}
