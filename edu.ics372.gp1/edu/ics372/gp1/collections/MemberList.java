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
		while(members.listIterator().hasNext()) {
			if(memberId.equals(members.listIterator().next().getId())) {
				return members.listIterator().next();
			}
		}
		return null;
	}
	
	public Iterator<Member> getMembers(){
		return members.listIterator();
	}
	
	public boolean removeMember(String memberId) {
		Iterator<Member> memberList = members.listIterator();
		
		while(memberList.hasNext()) {
			String memberId1 = memberList.next().getId();
			if(memberId.equals(memberId1)) {
				memberList.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean isMember(String memberId) {
		while(members.listIterator().hasNext()) {
			if(memberId.equals(members.listIterator().next().getId())) {
				return members.listIterator().next().isMember();
			}
		}
		return false;
	}
}

