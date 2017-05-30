package com.bridgew.project.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FoodItem {
	private IntegerProperty fid;
	private StringProperty name;
	private StringProperty cost;
	
	public StringProperty resFoodItemsProperty() {
		// TODO Auto-generated method stub
		return name;
	}

	public IntegerProperty getFid() {
		return fid;
	}

	public void setFid(IntegerProperty fid) {
		this.fid = fid;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public StringProperty getCost() {
		return cost;
	}

	public void setCost(StringProperty cost) {
		this.cost = cost;
	}

	public FoodItem() {
		super();
		this.fid = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.cost = new SimpleStringProperty();
	}
	
	
	

}
