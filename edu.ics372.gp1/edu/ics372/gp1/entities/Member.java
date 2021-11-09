package edu.ics372.gp1.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

/**
 * This class represents the Member object which contains information of a
 * registered member with name, address, phone number, date joined, fee paid,
 * and membership status
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
	 * This constructs the member object and generates its unique id.
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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Calendar getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Calendar dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getFeePaid() {
		return feePaid;
	}

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
		return "Member ID: " + this.id + "\nMember Name: " + this.name + "\nAddress: " + this.address + "\nPhone: "
				+ this.phoneNumber + "\nDate Joined: " + this.dateJoined + "\nMembership: " + this.membership;

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
	 * @param output
	 * @throws IOException, ClassNotFoundException
	 */
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}
}
