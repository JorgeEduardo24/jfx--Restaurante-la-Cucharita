package model;

import java.util.List;

public class Saucer {
	private String nameSaucer;
	private double price;
	private List<Ingredient> ingredientsOfSaucer;
	
	public Saucer(String nameSaucer, double price, List<Ingredient> ingredientsOfSaucer) {
		this.nameSaucer = nameSaucer;
		this.price = price;
		this.ingredientsOfSaucer = ingredientsOfSaucer;
	}

	public String getNameSaucer() {
		return nameSaucer;
	}

	public void setNameSaucer(String nameSaucer) {
		this.nameSaucer = nameSaucer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Ingredient> getIngredientsOfSaucer() {
		return ingredientsOfSaucer;
	}

	public void setIngredientsOfSaucer(List<Ingredient> ingredientsOfSaucer) {
		this.ingredientsOfSaucer = ingredientsOfSaucer;
	}
		
	
}
