package edu.ics372.gp1.collections;

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

}
