package edu.ics372.gp1.entities;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Member Object Member object contain information of a registered member listed
 * below their name, address, phone number, date joined, fee paid, membership
 * status and transaction history
 * 
 * @author Andy Phan
 *
 */
public class Member {
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	private Calendar dateJoined;
	private double feePaid;
	private boolean membership;
	private static final String MEMBER_STRING = "M";
	private static int idCounter;

	/**
	 * Member constructor
	 * 
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param feePaid
	 */
	public Member(String name, String address, String phoneNumber, double feePaid) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.feePaid = feePaid;
		this.id = MEMBER_STRING + ++idCounter;
		this.dateJoined = Calendar.getInstance();
		this.membership = true;
	}

	/**
	 * @return member Id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return member Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set a member name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return member's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * set member's address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return member's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * set member's phone number
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return date member joined
	 */
	public Calendar getDateJoined() {
		return dateJoined;
	}

	/**
	 * set the date when a member is registered/joined
	 * 
	 * @param dateJoined
	 */
	public void setDateJoined(Calendar dateJoined) {
		this.dateJoined = dateJoined;
	}

	/**
	 * @return member's membership fee Paid
	 */
	public double getFeePaid() {
		return feePaid;
	}

	/**
	 * set member's fee Paid
	 * 
	 * @param feePaid
	 */
	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
		this.membership = true;
	}

	/**
	 * check if it is a member
	 * 
	 * @return membership
	 */
	public boolean isMember() {
		return membership;
	}

	/**
	 * construct a string for displaying member information.
	 */
	@Override
	public String toString() {
		return "member ID: " + this.id + ", Member name: " + this.name + "\nAddress: " + this.address + ", Phone: "
				+ this.phoneNumber + "\nDate Joined: " + this.dateJoined + ", membership: " + this.membership;

	}
}
