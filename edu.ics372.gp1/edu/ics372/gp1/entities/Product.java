package edu.ics372.gp1.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Comments here
 * @author dilli
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
	
	public Product(String name, int reorderLevel, int stock, double price) {
		this.name = name;
		this.reorderLevel = reorderLevel;
		this.stock = stock;
		this.price = price;
		this.id= String.valueOf(idCounter);
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
	 * Serializes the static idCounter field
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
	 * @return a Library object
	 */
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}
}
