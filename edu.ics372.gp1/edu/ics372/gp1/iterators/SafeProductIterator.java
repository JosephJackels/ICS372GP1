package edu.ics372.gp1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.gp1.entities.Product;
import edu.ics372.gp1.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Product objects. The user should supply an iterator to Product as the
 * parameter to the constructor.
 * 
 * @author Joseph Jackels
 *
 */
public class SafeProductIterator implements Iterator<Result> {
	private Iterator<Product> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeProductIterator must supply an Iterator to Product.
	 * 
	 * @param iterator Iterator<Product>
	 */
	public SafeProductIterator(Iterator<Product> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setProductFields(iterator.next());
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
