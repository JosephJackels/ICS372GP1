package edu.ics372.gp1.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.Transaction;

public class TransactionList {
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private static TransactionList transactionList;

	private TransactionList() {

	}

	public static TransactionList getInstance() {
		if (transactionList == null) {
			transactionList = new TransactionList();
		}
		return transactionList;
	}

	public Iterator<Transaction> getTransactions() {
		return transactions.iterator();
	}

	public boolean insertTransaction(Transaction transaction) {
		return transactions.add(transaction);
	}
}
