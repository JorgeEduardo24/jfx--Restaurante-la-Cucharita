package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SaucerList {
	private List<Saucer> saucers;
	private String FILE_EXPORT_TXT_PATH = "data/ExportedSaucersData.txt";
	private String FILE_SAVE_PATH = "data2/Saucers.apo2";

	public SaucerList() {
		saucers = new ArrayList<Saucer>();
	}

	public void addSaucer(String nameSaucer, double price, List<IngredientOfSaucer> ingredientsOfSaucer) {
		saucers.add(new Saucer(nameSaucer, price, ingredientsOfSaucer));
	}

	public List<Saucer> getSaucers() {
		return saucers;
	}
	
	
	
	public void exportSaucers() throws IOException {
		FileWriter fw = new FileWriter(FILE_EXPORT_TXT_PATH, true);
		for(int i=0; i<saucers.size();i++) {
			Saucer saucer = saucers.get(i);
			fw.write(saucer.getNameSaucer()+";"+saucer.getPrice()+";"+saucer.getIngredientsOfSaucer().toString()+"\n");
		}
		fw.close();
	}
	
	
	public void saveSaucers() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
		oos.writeObject(saucers);
		oos.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(FILE_SAVE_PATH);
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			saucers = (List<Saucer>) ois.readObject(); 
			ois.close();
		}
	}
}
