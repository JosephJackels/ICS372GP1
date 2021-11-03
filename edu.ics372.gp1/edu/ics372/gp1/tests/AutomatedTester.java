package edu.ics372.gp1.tests;

import edu.ics372.gp1.entities.Member;
import edu.ics372.gp1.facade.GroceryStore;
import edu.ics372.gp1.facade.Request;
import edu.ics372.gp1.facade.Result;

public class AutomatedTester {
	private GroceryStore groceryStore;
	private String[] names = { "n1", "n2", "n3" };
	private String[] addresses = { "a1", "a2", "a3" };
	private String[] phones = { "p1", "p2", "p3" };
	private double[] fees = { 1.0, 2.0, 3.0 };
	private Member[] members = new Member[3];

	public void testAll() {
		addMembersTest();
	}

	public void addMembersTest() {
		System.out.println("Testing add members");
		for (int count = 0; count < members.length; count++) {
			Request.instance().setMemberAddress(addresses[count]);
			Request.instance().setMemberName(names[count]);
			Request.instance().setMemberPhoneNumber(phones[count]);
			Request.instance().setMemberFeePaid(Double.toString(fees[count]));
			Result result = GroceryStore.instance().addMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberPhoneNumber().equals(phones[count]);
			assert result.getMemberFeePaid().equals(Double.toString(fees[count]));
		}
	}

	public static void main(String[] args) {
		// runs all tests
		new AutomatedTester().testAll();
	}

}
