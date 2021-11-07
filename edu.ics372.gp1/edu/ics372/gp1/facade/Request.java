package edu.ics372.gp1.facade;

import java.util.Calendar;

/**
 * This class is used for requesting many of the results of the GroceryStore
 * system's business logic to user interface. It is a singleton
 * 
 * The Request object sets values of selected fields of Product, Order, Member,
 * and Transaction for the GroceryStore to use to perform actions requested by
 * the UserInterface.
 * 
 * @author Joseph Jackels
 *
 */
public class Request extends DataTransfer {
	private static Request request;
	private Calendar startDate;
	private Calendar endDate;

	/**
	 * Singleton class - private constructor
	 */
	private Request() {
		super();
	}

	/**
	 * method for getting singleton instance
	 * 
	 * @return - singleton instance of Request
	 */
	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}
