package edu.ics372.gp1.facade;

import edu.ics372.gp1.collections.MemberList;
import edu.ics372.gp1.collections.OrderList;
import edu.ics372.gp1.collections.ProductList;
import edu.ics372.gp1.collections.TransactionList;

public class GroceryStore {
	private MemberList members = MemberList.getInstance();
	private OrderList orders = OrderList.getInstance();
	private ProductList products = ProductList.getInstance();
	private TransactionList transactions = TransactionList.getInstance();

	private static GroceryStore groceryStore;

	private GroceryStore() {

	}

	public static GroceryStore instance() {
		if (groceryStore == null) {
			groceryStore = new GroceryStore();
		}

		return groceryStore;
	}
}
