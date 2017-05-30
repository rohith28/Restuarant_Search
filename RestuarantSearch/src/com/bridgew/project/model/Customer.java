package com.bridgew.project.model;

public class Customer {

	private int cid;
	private String name;
	private String email;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Customer(int cid, String name, String email) {
		super();
		this.cid = cid;
		this.name = name;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", name=" + name + ", email=" + email + "]";
	}
	
}
