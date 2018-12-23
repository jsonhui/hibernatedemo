package com.jason.hibernatedemo.bean;

import java.util.Set;

public class Author {

	private Integer id;
	private String name;
	private Set<Book> books;

	public Author() {
	}

	public Author(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
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
		return "Author [id=" + id + ", name=" + name + "]";
	}

}
