package edu.ics372.gp1.entities;

/**
 * This class represents the Product the grocery store carries. Products are
 * stocked after their shipment is received. Each product are stocked with
 * unique name along with unique id, quantity, price, and a minimum reorder
 * level.
 * 
 * @author Dilli
 *
 */
public class Product {
	private String name;
	private String id;
	private int reorderLevel;
	private int stock;
	private double price;
	private static int idCounter = 0;

	/**
	 * Constructs the product object
	 * 
	 * @param name         - name of the product
	 * @param reorderLevel - minimum reordering threshold
	 * @param stock        - number of product stocked/ to be stock.
	 * @param price        - unit price of the product
	 */
	public Product(String name, int reorderLevel, int stock, double price) {
		this.name = name;
		this.reorderLevel = reorderLevel;
		this.stock = stock;
		this.price = price;
		this.id = String.valueOf(idCounter);
		idCounter++;
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

	public void addStock(int quantity) {
		this.stock += quantity;
	}

	/**
	 * This method displays the product information in a nicely formatted way.
	 */
	@Override
	public String toString() {
		return "Product ID: " + this.id + "\nProduct Name: " + this.name + "\nPrice: " + this.price + "\nOrder Level: "
				+ this.reorderLevel + "\nQuantity in Stock: " + this.stock;
	}
}
