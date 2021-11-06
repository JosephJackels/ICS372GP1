package edu.ics372.gp1.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Member Object - Member object contains information of a registered member
 * listed below their name, address, phone number, date joined, fee paid, and
 * membership status
 * 
 * @author Andy Phan
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	private Calendar dateJoined;
	private String feePaid;
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
	public Member(String name, String address, String phoneNumber, String feePaid) {
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
	public String getFeePaid() {
		return feePaid;
	}

	/**
	 * set member's fee Paid
	 * 
	 * @param feePaid
	 */
	public void setFeePaid(String feePaid) {
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

	/**
	 * Serializes the static idCounter field
	 * 
	 * @param output
	 * @throws IOException
	 */
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	/**
	 * Retrieves a deserialized version of the static idCounter field from disk
	 * 
	 * @return a Library object
	 */
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}
}
