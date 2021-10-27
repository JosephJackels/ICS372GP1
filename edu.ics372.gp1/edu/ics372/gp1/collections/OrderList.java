package edu.ics372.gp1.collections;

import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.entities.Order;

public class OrderList {
	private List<Order> orders = new LinkedList<Order>();
	private static OrderList orderList;

	private OrderList() {

	}

	public static OrderList getInstance() {
		if (orderList == null) {
			orderList = new OrderList();
		}
		return orderList;
	}
}
