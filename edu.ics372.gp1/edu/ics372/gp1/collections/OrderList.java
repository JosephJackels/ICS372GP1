package edu.ics372.gp1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.entities.Order;

/**
 * OrderList class, maintains list of order objects. 
 * @author leo
 *
 */
public class OrderList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new LinkedList<Order>();
	private static OrderList orderList;

	/**
	 * Private constructor for singleton.
	 */
	private OrderList() {
		
	}
	
	/**
	 * Returns single object, if it has not been created already.
	 * 
	 * @return instance of orderList.
	 */
	public static OrderList getInstance() {
		if (orderList == null) {
			orderList = new OrderList();
		}
		return orderList;
	}
	
	/**
	 * Add order to list.
	 * 
	 * @param order
	 * @return true if an order can be added.
	 */
	public boolean addOrder(Order order) {
		orders.add(order);
		return true;
	}
	
	/**
	 * Removes order from the list, given product ID.
	 * 
	 * @param productID
	 * @return true if order can be removed.
	 */
	public boolean removeOrder(String productID){
		Order newOrder = search(productID);
		if (newOrder == null) {
			return false;
		} else {
			return orders.remove(newOrder);
		}
	}
	
	/**
	 * Search the list of orders by product ID. 
	 * 
	 * @param productID
	 * @return order if found
	 */
	public Order search(String productID){
		Order order = null;
		for(Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			order = (Order) iterator.next();
			if(order.getProductID().equals(productID)) {
				return order;
			}
		}
		return null;
	}
	
	/**
	 * Returns an iterator for the OrderList.
	 * 
	 * @return iterator of OrderList
	 */
	public Iterator<Order> iterator() {
		return orders.iterator();
	}
	
	@Override
	public String toString() {
		return orders.toString();
	}
}
