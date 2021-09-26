package model;

import java.util.ArrayList;
import java.util.List;

public class IngredientsList {
	private List<Ingredient> ingredients;

	public IngredientsList() {
		ingredients = new ArrayList<Ingredient>();
	}
	
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
	}
	
	public List<Ingredient> getIngredients(){
		return ingredients;
	}

}
