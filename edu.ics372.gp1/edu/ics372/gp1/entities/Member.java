package edu.ics372.gp1.entities;

import java.util.Calendar;

public class Member {
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	private Calendar dateJoined;
	private double feePaid;

	public Member(String name, String address, String phoneNumber, double feePaid) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.feePaid = feePaid;
		// id???
		this.dateJoined = Calendar.getInstance();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}
}
