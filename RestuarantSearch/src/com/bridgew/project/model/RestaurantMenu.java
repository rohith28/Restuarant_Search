package com.bridgew.project.model;

import javafx.beans.property.IntegerProperty;

public class RestaurantMenu {
	private IntegerProperty menu;
	private IntegerProperty foodItem;
	public IntegerProperty getMenu() {
		return menu;
	}
	public void setMenu(IntegerProperty menu) {
		this.menu = menu;
	}
	public IntegerProperty getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(IntegerProperty foodItem) {
		this.foodItem = foodItem;
	}
	@Override
	public String toString() {
		return "RestaurantMenu [menu=" + menu + ", foodItem=" + foodItem + "]";
	}
	public RestaurantMenu(IntegerProperty menu, IntegerProperty foodItem) {
		super();
		this.menu = menu;
		this.foodItem = foodItem;
	}
	

}
