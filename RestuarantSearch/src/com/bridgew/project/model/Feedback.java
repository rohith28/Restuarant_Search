package com.bridgew.project.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Feedback {

	private IntegerProperty resId;
	private IntegerProperty custId;
	private IntegerProperty rating;
	public IntegerProperty getResId() {
		return resId;
	}
	public void setResId(IntegerProperty resId) {
		this.resId = resId;
	}
	public IntegerProperty getCustId() {
		return custId;
	}
	public void setCustId(IntegerProperty custId) {
		this.custId = custId;
	}
	public IntegerProperty getRating() {
		return rating;
	}
	public void setRating(IntegerProperty rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Feedback [resId=" + resId + ", custId=" + custId + ", rating=" + rating + "]";
	}
	
	
	
	public Feedback() {
		super();
		this.resId = new SimpleIntegerProperty();
		this.custId = new SimpleIntegerProperty();
		this.rating = new SimpleIntegerProperty();
	}
	public IntegerProperty resRatingColumnProperty() {
		// TODO Auto-generated method stub
		return rating;
	}
	
	
}
