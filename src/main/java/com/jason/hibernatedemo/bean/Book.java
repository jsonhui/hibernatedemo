package com.jason.hibernatedemo.bean;

public class Book {

	private Integer id;
	private String name;
	private Integer athorName;

	public Book() {
	}

	public Book(Integer id, String name, Integer athorName) {
		this.id = id;
		this.name = name;
		this.athorName = athorName;
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

	public Integer getAthorName() {
		return athorName;
	}

	public void setAthorName(Integer athorName) {
		this.athorName = athorName;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", athorName=" + athorName + "]";
	}

}
