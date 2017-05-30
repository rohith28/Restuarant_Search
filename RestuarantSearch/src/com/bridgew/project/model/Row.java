/*
 * Class Name : Row
 * Author : Rohith Uppala,Mounika Uppala 
 * Version : 1.1  
 * Description : This class is used for creating the Row class which is used to write in table of GUI 
 * 
 * 
 * */

package com.bridgew.project.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Row {
	
	private SimpleIntegerProperty rid;
	private SimpleStringProperty name;
	private SimpleStringProperty address;
	@Override
	public String toString() {
		return "Row [rid=" + rid + ", name=" + name + ", address=" + address + ", phone=" + phone + ", rating=" + rating
				+ ", timmings=" + timmings + ", FoodItem=" + FoodItem + "]";
	}


	private SimpleStringProperty phone;
	private SimpleFloatProperty rating;
	private SimpleStringProperty timmings;
	private SimpleStringProperty FoodItem;
	
	
	public Row(int rid, String name, String address, String phone,
			float rating, String timmings, String foodItem) {
		super();
		this.rid = new SimpleIntegerProperty(rid);
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.phone = new SimpleStringProperty(phone);
		this.rating = new SimpleFloatProperty(rating);
		this.timmings = new SimpleStringProperty(timmings);
		this.FoodItem = new SimpleStringProperty(foodItem);
	}


	public SimpleIntegerProperty resIdColumnProperty() {
		// TODO Auto-generated method stub
		return rid;
	}


	public SimpleStringProperty resNameColumnProperty() {
		// TODO Auto-generated method stub
		return name;
	}


	public SimpleStringProperty resAddressColumnProperty() {
		// TODO Auto-generated method stub
		return address;
	}


	public SimpleStringProperty resFoodItemsProperty() {
		// TODO Auto-generated method stub
		return FoodItem;
	}


	public SimpleStringProperty resPhoneColumnProperty() {
		// TODO Auto-generated method stub
		return phone;
	}


	public SimpleFloatProperty resRatingColumnProperty() {
		// TODO Auto-generated method stub
		return rating;
	}


	public SimpleStringProperty resTimmingsColumnProperty() {
		// TODO Auto-generated method stub
		return timmings;
	}


	public int getRid() {
		return rid.get();
	}


	public SimpleStringProperty getName() {
		return name;
	}


	public SimpleStringProperty getAddress() {
		return address;
	}


	public SimpleStringProperty getPhone() {
		return phone;
	}


	public float getRating() {
		return rating.get();
	}


	public SimpleStringProperty getTimmings() {
		return timmings;
	}


	public SimpleStringProperty getFoodItem() {
		return FoodItem;
	}
		

}
