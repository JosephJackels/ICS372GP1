package edu.ics372.gp1.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Order object, an order consists of an order ID, product, that product's ID
 * and the quantity of the product.
 * 
 * @author leo
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderID;
	private String productID;
	private Product product;
	private int quantity;
	private static int idCounter = 0;

	/**
	 * Constructs order, creates unique ID.
	 * 
	 * @param productID
	 * @param quantity
	 * @param product
	 */
	public Order(Product product, int quantity) {
		this.productID = product.getId();
		this.quantity = quantity;
		this.orderID = String.valueOf(idCounter);
		this.product = product;
		idCounter++;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderID() {
		return orderID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	/**
	 * This Constructs string representation for an order.
	 */
	@Override
	public String toString() {
		return "Order: " + orderID + ", product: " + productID + ", " + product.getName() + ", quantity: " + quantity;
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
	 * @param output
	 * @throws IOException, ClassNotFoundException
	 */
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}

}
