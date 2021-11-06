package edu.ics372.gp1.entities;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Transaction Object, contains the memberID of the member that made the
 * transaction (made a purchase through the GroceryStore), the date that the
 * transaction occurred on, and the total price of the transaction.
 * 
 * @author Joseph Jackels
 *
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private String memberID;
	private Calendar date;
	private double totalPrice;

	/**
	 * Constructs the Transaction, sets the date by getting the current date.
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
	 * Checks that the transaction was made by the given member, and that the date
	 * that this transaction occurred on is between the start dates (inclusive) in
	 * other words, the date must be both on or after the start date, and on or
	 * before the end date.
	 * 
	 * @param memberID
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean checkTransaction(String memberID, Calendar startDate, Calendar endDate) {

		if (!this.memberID.equals(memberID)) {
			return false;
		}

		if (this.date.before(startDate) || this.date.after(endDate)) {
			return false;
		}

		return true;
	}

}
