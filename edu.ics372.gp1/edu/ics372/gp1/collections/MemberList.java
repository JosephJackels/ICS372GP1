package edu.ics372.gp1.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.entities.Member;

public class MemberList {
	private List<Member> members = new LinkedList<Member>();
	private static MemberList memberList;

	private MemberList() {

	}

	public static MemberList getInstance() {
		if (memberList == null) {
			memberList = new MemberList();
		}
		return memberList;
	}
	
	public boolean insertMember(Member member) {
		return members.add(member);
	}
	
	public Member getMember(String memberId) {
		Iterator<Member> iterator = members.listIterator();
		while(iterator.hasNext()) {
			Member member = iterator.next();
			if(memberId.equals(member.getId())) {
				return member;
			}
		}
		return null;
	}
	
	public Iterator<Member> getMembers(){
		return members.listIterator();
	}
	
	public boolean removeMember(String memberId) {
		Iterator<Member> iterator = members.listIterator();
		while(iterator.hasNext()) {
			String memberId1 = iterator.next().getId();
			if(memberId.equals(memberId1)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean isMember(String memberId) {
		Iterator<Member> iterator = members.listIterator();
		while(iterator.hasNext()) {
			Member member = iterator.next();
			if(memberId.equals(member.getId())) {
				return member.isMember();
			}
		}
		return false;
	}
}

