package edu.ics372.gp1.entities;

public class Product {
	private String name;
	private String id;
	private int reorderLevel;
	private int stock;
	private double price;

	public Product(String name, int reorderLevel, int stock, double price) {
		this.name = name;
		this.reorderLevel = reorderLevel;
		this.stock = stock;
		this.price = price;

		// id??
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
