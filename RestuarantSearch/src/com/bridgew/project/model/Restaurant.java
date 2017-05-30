package com.bridgew.project.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Restaurant {
	
	private IntegerProperty rid;
	private StringProperty name;
	private StringProperty address;
	private StringProperty phone;
	private StringProperty timmings;
	private String fooditem;
	private  IntegerProperty rating;
	
	
	
	
	public int getRating() {
		return rating.get();
	}


	public void setRating(int rating) {
		this.rating.set(rating);;
	}


	public String getFooditem() {
		return fooditem;
	}


	public void setFooditem(String fooditem) {
		this.fooditem = fooditem;
	}


	public int getRid() {
		return rid.get();
	}


	public void setRid(int rid) {
		this.rid.set(rid);
	}


	public String getName() {
		return name.get();
	}


	public void setName(String name) {
		this.name.set(name);
	}


	public String getAddress() {
		return address.get();
	}


	public void setAddress(String address) {
		this.address.set(address);;
	}


	public String getPhone() {
		return phone.get();
	}


	public void setPhone(String phone) {
		this.phone.set(phone);
	}


	public String getTimmings() {
		return timmings.get();
	}


	public void setTimmings(String timmings) {
		this.timmings.set(timmings);
	}


	public Restaurant() {
		super();
		this.rid = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.address = new SimpleStringProperty();
		this.phone =  new SimpleStringProperty();
		this.timmings = new SimpleStringProperty();
	}

	public IntegerProperty resIdColumnProperty() {
		// TODO Auto-generated method stub
		return rid;
	}


	public StringProperty resNameColumnProperty() {
		// TODO Auto-generated method stub
		return name;
	}


	public StringProperty resAddressColumnProperty() {
		// TODO Auto-generated method stub
		return address;
	}


	public  StringProperty resPhoneColumnProperty() {
		// TODO Auto-generated method stub
		return phone;
	}


	public StringProperty resTimmingsColumnProperty() {
		// TODO Auto-generated method stub
		return timmings;
	}


	@Override
	public String toString() {
		return "Restaurant [rid=" + rid + ", name=" + name + ", address=" + address + ", phone=" + phone + ", timmings="
				+ timmings + ", fooditem=" + fooditem + ", rating=" + rating + "]";
	}


	public String resFoodItemsProperty() {
		// TODO Auto-generated method stub
		return fooditem;
	}


	public IntegerProperty resRatingColumnProperty() {
		// TODO Auto-generated method stub
		return rating;
	}
	

	
}
