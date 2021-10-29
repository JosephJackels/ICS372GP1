package edu.ics372.gp1.entities;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Member {
	private String id;
	private String name;
	private String address;
	private String phoneNumber;
	private Calendar dateJoined;
	private double feePaid;
	private boolean membership;
	private List<Transaction> transactions = new LinkedList<>();
	
	public Member(String name, String address, String phoneNumber, double feePaid) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.feePaid = feePaid;
		this.id = UUID.randomUUID().toString();
		this.dateJoined = Calendar.getInstance();
		this.membership = true;
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
	
	public boolean isMember() {
		return membership;
	}
	
	/*
	 * get transaction iterator
	 * @return iterator
	 */
	Iterator<Transaction> getTransactions(){
		return transactions.iterator();
	}
	
	LinkedList<Transaction> getTransactions(Calendar date){
		LinkedList<Transaction> transactionList = new LinkedList<>();
		Iterator<Transaction> iterator = transactions.listIterator();
		while(iterator.hasNext()) {
			Transaction transaction = iterator.next();
			if(date.equals(transaction.getDate())) {
				transactionList.add(transaction);
			}
		}
		return transactionList;
	}
}
