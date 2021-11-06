package edu.ics372.gp1.collections;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.entities.Transaction;

/**
 * Transaction list class, maintains a list of transactions.
 * 
 * @author Joseph Jackels
 *
 */
public class TransactionList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private static TransactionList transactionList;

	/**
	 * Private constructor for singleton.
	 */
	private TransactionList() {

	}

	/**
	 * Transaction list instance method, creates the sole instance of transaction
	 * list.
	 * 
	 * @return
	 */
	public static TransactionList getInstance() {
		if (transactionList == null) {
			transactionList = new TransactionList();
		}
		return transactionList;
	}

	/**
	 * Returns an iterator for the transaction list.
	 * 
	 * @return
	 */
	public Iterator<Transaction> getTransactions() {
		return transactions.iterator();
	}

	/**
	 * Inserts a transaction object to the list.
	 * 
	 * @param transaction
	 * @return
	 */
	public boolean insertTransaction(Transaction transaction) {
		return transactions.add(transaction);
	}

	/**
	 * Returns an iterator to a list of transactions between a specific date.
	 * 
	 * @param memberId
	 * @param startDate
	 * @param EndDate
	 * @return
	 */
	public Iterator<Transaction> getTransactions(String memberId, Calendar startDate, Calendar endDate) {
		return new FilteredIterator(transactions.iterator(),
				transaction -> transaction.checkTransaction(memberId, startDate, endDate)).getIterator();
		/*
		 * LinkedList<Transaction> transactionList = new LinkedList<Transaction>();
		 * Iterator<Transaction> iterator = transactions.iterator(); while
		 * (iterator.hasNext()) { Transaction transaction = iterator.next(); if
		 * (transaction.checkTransaction(memberId, startDate, endDate)) {
		 * transactionList.add(transaction); } } return transactionList.iterator();
		 */
	}
}
