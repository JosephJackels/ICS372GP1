package edu.ics372.gp1.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class represents the product the grocery store carries. Each product has
 * a unique name and id, and is associated with minimum reorder level. Product
 * is ordered when it reduces to minimum reorder level.
 * 
 * @author Dilli
 *
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private int reorderLevel;
	private int stock;
	private double price;
	private static int idCounter = 0;

	/**
	 * This constructs the product object and initializes its id
	 * 
	 * @param name         - name of the product
	 * @param reorderLevel - minimum reorder level
	 * @param stock        - number of products in the stock
	 * @param price        - unit price of the product
	 */
	public Product(String name, int reorderLevel, int stock, double price) {
		this.name = name;
		this.reorderLevel = reorderLevel;
		this.stock = stock;
		this.price = price;
		idCounter++;
		this.id = String.valueOf(idCounter);
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
	 * Serializes the static idCounter field of the product object
	 * 
	 * @param output
	 * @throws IOException
	 */
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	/**
	 * Retrieves a deserialized version of the static idCounter field from disk
	 * 
	 * @param input
	 * @throws IOException, ClassNotFoundException
	 */
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}

	/**
	 * This method constructs and prints the string representation of the product
	 * object.
	 */
	@Override
	public String toString() {
		return "Product ID: " + this.id + "\nProduct Name: " + this.name + "\nPrice: " + this.price
				+ "\nStock Quantity: " + this.stock + "\nReroder Level: " + this.reorderLevel;
	}
}
