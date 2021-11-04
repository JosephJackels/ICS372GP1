package edu.ics372.gp1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.gp1.entities.Transaction;
import edu.ics372.gp1.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Transaction objects. The user should supply an iterator to Transaction as
 * the parameter to the constructor.
 * 
 * @author Joseph Jackels
 *
 */
public class SafeTransactionIterator implements Iterator<Result> {
	private Iterator<Transaction> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeTransactionIterator must supply an Iterator to Transaction.
	 * 
	 * @param iterator Iterator<Transaction>
	 */
	public SafeTransactionIterator(Iterator<Transaction> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setTransactionFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
