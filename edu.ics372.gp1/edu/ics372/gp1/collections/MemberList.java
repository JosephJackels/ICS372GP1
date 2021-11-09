package edu.ics372.gp1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.entities.Member;
import edu.ics372.gp1.iterators.FilteredIterator;

/**
 * MemberList class is use maintain a list of Member objects
 * 
 * @author Andy
 *
 */
public class MemberList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Member> members = new LinkedList<Member>();
	private static MemberList memberList;

	/**
	 * memberList constructor.
	 */
	private MemberList() {

	}

	/**
	 * Member list instance method, creates the sole instance of member list.
	 * 
	 * @return instance of memberList
	 */
	public static MemberList getInstance() {
		if (memberList == null) {
			memberList = new MemberList();
		}
		return memberList;
	}

	/**
	 * adding member to the list
	 * 
	 * @param member
	 * @return true or false.
	 */
	public boolean insertMember(Member member) {
		return members.add(member);
	}

	/**
	 * get data and information of a member given their ID
	 * 
	 * @param memberId
	 * @return member, null if there are no member found
	 */
	public Member getMember(String memberId) {
		Iterator<Member> iterator = members.listIterator();
		while (iterator.hasNext()) {
			Member member = iterator.next();
			if (memberId.equals(member.getId())) {
				return member;
			}
		}
		return null;
	}

	/**
	 * @return iterator of a memberList
	 */
	public Iterator<Member> getMembers() {
		return members.listIterator();
	}

	/**
	 * Searches the member list for all members that have a given name
	 * 
	 * @param memberName - name of member(s) to search for
	 * @return Iterator<Member> to the list of Members that have the given name
	 */
	public Iterator<Member> getMembersByName(String memberName) {

		return new FilteredIterator<Member>(members.iterator(), member -> member.getName().equals(memberName));
	}

	/**
	 * Removes a member from the memberList
	 * 
	 * @param memberId
	 * @return true if member could be removed
	 */
	public boolean removeMember(String memberId) {
		Member member = getMember(memberId);
		if (member == null) {
			return false;
		} else {
			return members.remove(member);
		}
	}

	/**
	 * check a member's membership status given their Id
	 * 
	 * @param memberId
	 * @return true if is a member, false if not.
	 */
	public boolean isMember(String memberId) {
		Iterator<Member> iterator = members.listIterator();
		while (iterator.hasNext()) {
			Member member = iterator.next();
			if (memberId.equals(member.getId())) {
				return member.isMember();
			}
		}
		return false;
	}
}
