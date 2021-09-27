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

public class OrderList {
	private List<Order> orders;
	private String FILE_EXPORT_TXT_PATH = "data/ExportedOrderData.txt";
	private String FILE_SAVE_PATH = "data2/Orders.apo2";

	public OrderList() {
		orders = new ArrayList<Order>();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public List<Order> getOrders() {
		return orders;
	}
	
	
	public void exportOrders() throws IOException {
		FileWriter fw = new FileWriter(FILE_EXPORT_TXT_PATH, true);
		for(int i=0; i<orders.size();i++) {
			Order order = orders.get(i);
			fw.write(order.getSaucersOfOrder().toString()+";"+order.getStatus()+";"+order.getOrderDate()+"\n");
		}
		fw.close();
	}
	
	
	
	public void saveOrders() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
		oos.writeObject(orders);
		oos.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(FILE_SAVE_PATH);
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			orders = (List<Order>) ois.readObject(); 
			ois.close();
		}
	}
}
