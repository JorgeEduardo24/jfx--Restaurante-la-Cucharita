package model;

import java.util.ArrayList;
import java.util.List;

public class SaucerList {
private List<Saucer> saucers;
	
	public SaucerList() {
		saucers = new ArrayList<Saucer>();
	}
	
	public void addSaucer(String name, double price, List<IngredientOfSaucer> ingredientsOfSaucer) {
		saucers.add(new Saucer(name, price, ingredientsOfSaucer));
	}
	
	public List<Saucer> getSaucers(){
		return saucers;
	}
}
