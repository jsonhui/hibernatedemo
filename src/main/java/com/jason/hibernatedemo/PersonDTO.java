package com.jason.hibernatedemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_person")
public class PersonDTO {
	@Id
	@Column(name = "p_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "p_name")
	private String name;
	@Column(name = "p_money")
	private String money;

	public PersonDTO() {
	}

	public PersonDTO(String name, String money) {
		this.name = name;
		this.money = money;
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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", name=" + name + ", money=" + money + "]";
	}

}
