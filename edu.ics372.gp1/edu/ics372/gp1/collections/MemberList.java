package edu.ics372.gp1.collections;

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
	//HELLO it this working?
}
