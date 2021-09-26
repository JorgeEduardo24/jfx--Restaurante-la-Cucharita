package model;

import java.util.List;
import java.util.UUID;

public class Order {
	private UUID uniqueKey;
	private List<Saucer> saucersOfOrder;
	private String status;
	private String orderDate;
	
	public Order(List<Saucer> saucersOfOrder, String status, String orderDate) {
		this.uniqueKey = UUID.randomUUID();
		this.saucersOfOrder = saucersOfOrder;
		this.status = status;
		this.orderDate = orderDate;
	}

	public UUID getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(UUID uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public List<Saucer> getSaucersOfOrder() {
		return saucersOfOrder;
	}

	public void setSaucersOfOrder(List<Saucer> saucersOfOrder) {
		this.saucersOfOrder = saucersOfOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}
