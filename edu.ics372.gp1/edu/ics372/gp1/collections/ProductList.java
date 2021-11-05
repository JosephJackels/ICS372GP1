package edu.ics372.gp1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.entities.Product;

/**
 * Product list class, maintains list of products.
 * @author 
 *
 */
public class ProductList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Product> products = new LinkedList<Product>();
	private static ProductList productList;

	/**
	 * Product list private constructor for singleton.
	 */
	private ProductList() {

	}

	/**
	 * Product list instance method, creates sole instance of 
	 * product list.
	 * @return
	 */
	public static ProductList getInstance() {
		if (productList == null) {
			productList = new ProductList();
		}
		return productList;
	}

	/**
	 * Returns an iterator for the product list.
	 * @return
	 */
	public Iterator<Product> getIterator() {
		return products.iterator();
	}

	/**
	 * Adds the product to the list.
	 * @param product
	 * @return
	 */
	public boolean insertProduct(Product product) {
		return products.add(product);
	}

	/**
	 * Check if a name is unused by any of the products.
	 * @param name
	 * @return
	 */
	public boolean nameAvailable(String name) {
		Iterator<Product> iterator = getInstance().getIterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getName().equals(name)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if a product exists.
	 * @param productId
	 * @return
	 */
	public boolean isProduct(String productId) {
		Iterator<Product> iterator = getInstance().getIterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getId().equals(productId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if a product's stock is sufficient.
	 * @param productId
	 * @param stock
	 * @return
	 */
	public boolean hasStock(String productId, int stock) {
		Iterator<Product> iterator = getInstance().getIterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getId().equals(productId)) {
				return (product.getStock() >= stock);
			}
		}
		return false;
	}

	/**
	 * Search the list of by product ID.
	 * @param productId
	 * @return
	 */
	public Product getProductById(String productId) {
		Iterator<Product> iterator = getInstance().getIterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getId().equals(productId)) {
				return product;
			}
		}
		return null;
	}

	/**
	 * Search the list of products by a specific name.
	 * @param productName
	 * @return
	 */
	public Product getProductByName(String productName) {
		Iterator<Product> iterator = getInstance().getIterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			if (product.getName().equals(productName)) {
				return product;
			}
		}
		return null;
	}
}
