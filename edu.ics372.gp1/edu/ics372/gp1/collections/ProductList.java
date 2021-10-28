package edu.ics372.gp1.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.entities.Product;

public class ProductList {
	private List<Product> products = new LinkedList<Product>();
	private static ProductList productList;

	private ProductList() {

	}

	public static ProductList getInstance() {
		if (productList == null) {
			productList = new ProductList();
		}
		return productList;
	}

	public Iterator<Product> getIterator() {
		return products.iterator();
	}

	public boolean insertProduct(Product product) {
		return products.add(product);
	}

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

}
