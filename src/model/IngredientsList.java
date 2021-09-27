package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class IngredientsList {
	private List<Ingredient> ingredients;
	private String FILE_IMPORT_TXT_PATH = "data/IngredientsData.txt";
	private String FILE_EXPORT_TXT_PATH = "data/ExportedIngredientsData.txt";
	private String FILE_SAVE_PATH = "data2/Ingredients.apo2";

	public IngredientsList() {
		ingredients = new ArrayList<Ingredient>();
	}
	
	public void addIngredient(String ingredientName, double quantity, String quantityUnits) {
		ingredients.add(new Ingredient(ingredientName, quantity, quantityUnits ));
	}
	
	public List<Ingredient> getIngredients(){
		return ingredients;
	}
	
	
	public void exportIngredients() throws IOException {
		FileWriter fw = new FileWriter(FILE_EXPORT_TXT_PATH, true);
		for(int i=0; i<ingredients.size();i++) {
			Ingredient ingredient = ingredients.get(i);
			fw.write(ingredient.getIngredientName()+";"+ingredient.getQuantity()+";"+ingredient.getQuantityUnits()+"\n");
		}
		fw.close();
	}
	
	
	public void importIngredients() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_TXT_PATH));
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String[] parts = line.split(";");
			String ingredientName = parts[0];
			double quantity = Double.parseDouble(parts[1]);
			String quantityUnits = parts[2];
			addIngredient(ingredientName,quantity,quantityUnits );
			line = br.readLine();
		}
		br.close();
	}
	
	
	public void saveIngredients() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
		oos.writeObject(ingredients);
		oos.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadIngredients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(FILE_SAVE_PATH);
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			ingredients = (List<Ingredient>) ois.readObject(); 
			ois.close();
		}
	}
}
