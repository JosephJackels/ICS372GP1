package edu.ics372.gp1.entities;

import java.util.Calendar;

/**
 * This class represents the transaction object for each customer of the grocery
 * store.
 * 
 * @author Joseph
 *
 */
public class Transaction {
	private String memberID;
	private Calendar date;
	private double totalPrice;

	/**
	 * Constructs transaction object
	 * 
	 * @param memberID
	 * @param totalPrice
	 */
	public Transaction(String memberID, double totalPrice) {
		this.memberID = memberID;
		this.totalPrice = totalPrice;
		this.date = Calendar.getInstance();
	}

	public String getMemberID() {
		return this.memberID;
	}

	public Calendar getDate() {
		return this.date;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	/**
	 * This method checks the transaction of a particular member in the specified
	 * time period.
	 * 
	 * @param memberID  - ID of the member
	 * @param startDate - start date for generating transaction
	 * @param endDate   - end date for generating transaction for member
	 * @return - true or false
	 */
	public boolean checkTransaction(String memberID, Calendar startDate, Calendar endDate) {

		if (this.memberID != memberID) {
			return false;
		}

		if (this.date.before(startDate) || this.date.after(endDate)) {
			return false;
		}

		return true;
	}
}
