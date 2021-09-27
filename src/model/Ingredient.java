package model;

import java.io.Serializable;

public class Ingredient implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String ingredientName;
	protected String quantityUnits;
	protected double quantity;
	
	public Ingredient(String ingredientName, double quantity, String quantityUnits) {
		this.ingredientName = ingredientName;
		this.quantityUnits = quantityUnits;
		this.quantity = quantity;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getQuantityUnits() {
		return quantityUnits;
	}

	public void setQuantityUnits(String quantityUnits) {
		this.quantityUnits = quantityUnits;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
	public int compareByIngredientName(Ingredient otherIngredientName) {
		return ingredientName.compareTo(otherIngredientName.getIngredientName());
	}
	
	
}
