package edu.ics372.gp1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * This class implements the Iterator interface to iterate only on items that
 * satisfy a certain predicate.
 * 
 * @author Austin Wang
 *
 * @param <T> the type of the item to be traversed
 */
public class FilteredIterator<T> implements Iterator<T> {
	private T item;
	private Predicate<T> predicate;
	private Iterator<T> iterator;

	/**
	 * Sets the iterator and predicate fields and positions to the first item that
	 * satisfies the predicate.
	 * 
	 * @param iterator  the iterator to the list
	 * @param predicate specifies the test
	 */
	public FilteredIterator(Iterator<T> iterator, Predicate<T> predicate) {
		this.predicate = predicate;
		this.iterator = iterator;
		getNextItem();
	}

	@Override
	public boolean hasNext() {
		return item != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No such element");
		}
		T returnValue = item;
		getNextItem();
		return returnValue;
	}

	/**
	 * This method searches for the next item that satisfies the predicate. If none
	 * is found, the item field is set to null.
	 */
	private void getNextItem() {
		while (iterator.hasNext()) {
			item = iterator.next();
			if (predicate.test(item)) {
				return;
			}
		}
		item = null;
	}

}
