package edu.ics372.gp1.entities;

/**
 * This class represents the Order object, which consists of an order ID,
 * product, product's ID and the quantity of the product.
 * 
 * @author leo
 *
 */
public class Order {
	private String orderID;
	private String productID;
	private Product product;
	private int quantity;
	private static int idCounter = 0;

	/**
	 * Constructs order, creates unique ID.
	 * 
	 * @param productID - ID of the product ordered/to be ordered
	 * @param quantity  - quantity of the product ordered/to be ordered
	 * @param product   - product object ordered/ to be ordered
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
	 * Constructs string representation for an order object.
	 */
	@Override
	public String toString() {
		return "Order: " + orderID + ", product: " + productID + ", " + product.getName() + ", quantity: " + quantity;
	}

}
