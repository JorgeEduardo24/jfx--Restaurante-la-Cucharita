package model;

import java.io.Serializable;
import java.util.List;

public class Saucer implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nameSaucer;
	private double price;
	private List<IngredientOfSaucer> ingredientsOfSaucer;
	
	public Saucer(String nameSaucer, double price, List<IngredientOfSaucer> ingredientsOfSaucer) {
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

	public List<IngredientOfSaucer> getIngredientsOfSaucer() {
		return ingredientsOfSaucer;
	}

	public void setIngredientsOfSaucer(List<IngredientOfSaucer> ingredientsOfSaucer) {
		this.ingredientsOfSaucer = ingredientsOfSaucer;
	}
		
	
}
