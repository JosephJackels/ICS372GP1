package edu.ics372.gp1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.gp1.entities.Member;
import edu.ics372.gp1.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Member objects. The user should supply an iterator to Member as the
 * parameter to the constructor.
 * 
 * @author Joseph Jackels
 *
 */
public class SafeMemberIterator implements Iterator<Result> {
	private Iterator<Member> iterator;
	private Result result = new Result();

	/**
	 * The user of SafeMemberIterator must supply an Iterator to Member.
	 * 
	 * @param iterator Iterator<Member>
	 */
	public SafeMemberIterator(Iterator<Member> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setMemberFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
