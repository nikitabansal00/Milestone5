package com.example.demo.entity;

public class OrderEntity {
	
	private long id;
	private String name;
	private long amount;
	private String date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderEntity(long id, String name, long amount, String date) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.date = date;
	}
	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", name=" + name + ", amount=" + amount + ", date=" + date + "]";
	}
	
	

}
