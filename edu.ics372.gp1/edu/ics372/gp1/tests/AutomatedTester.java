package edu.ics372.gp1.tests;

import java.util.Iterator;

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
	private String[] memberIds = { "", "", "" };
	private String[] productIds = { "", "", "" };
	private String[] productsName = { "Apple", "Pear", "Orange" };
	private String[] price = { "1.5", "2.5", "3.5" };
	private String[] newPrices = { "5.2", "1.0", "6.7" };
	private String[] reorderLevel = { "10", "11", "12" };

	public void testAll() {
		// tests separated with new lines so that groups that need
		// to be run in a certain order to test properly remain that way
		// e.x. getMemberInfoTest() depends on addMembersTest() being run before it

		addMembersTest();
		getMemberInfoTest();
		listAllMembersTest();

		addProductTest();
		getProductInfoTest();
		listAllProductsTest();
		changePriceTest();

		// TODO order tests
		listOutstandingOrdersTest();
		processShipmentTest();

		// TODO checkout tests
		checkoutTest();
		printTransactionsTest();

		// i put remove member last since we need member ID to do checkout test.
		removeMembersTest();
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
			assert result.getMemberAddress().equals(addresses[count]);
			assert result.getMemberPhoneNumber().equals(phones[count]);
			assert result.getMemberFeePaid().equals(Double.toString(fees[count]));

			// save created ID for future tests
			memberIds[count] = result.getMemberID();
		}
	}

	public void removeMembersTest() {
		System.out.println("Testing remove member");
		for (int count = 0; count < members.length; count++) {
			Request.instance().setMemberID(memberIds[count]);
			Result result = GroceryStore.instance().removeMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberAddress().equals(addresses[count]);
			assert result.getMemberPhoneNumber().equals(phones[count]);
			assert result.getMemberFeePaid().equals(Double.toString(fees[count]));
		}
	}

	public void getMemberInfoTest() {
		System.out.println("Testing get member(s) by name");
		for (int count = 0; count < members.length; count++) {
			Request.instance().setMemberName(names[count]);
			Iterator<Result> results = GroceryStore.instance().getMemberInfo(Request.instance());
			while (results.hasNext()) {
				Result result = results.next();
				assert result.getMemberName().equals(names[count]);
			}
		}
	}

	public void addProductTest() {
		/*
		 * in GP1 pdf it said when user add product, an order is created. so i've added that in GroceryStore, in addProduct method
		 * unless i'm wrong but i haven't see any order created after product is added.(except complete checkout).
		 * now we can do processShipment and outstanding order test.
		 */
		System.out.println("Testing add Product");
		for (int count = 0; count < 3; count++) {
			Request.instance().setProductName(productsName[count]);
			Request.instance().setProductReorderLevel(reorderLevel[count]);
			Request.instance().setProductPrice(price[count]);
			Result result = GroceryStore.instance().addProduct(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getOrderQuantity().equals(Integer.toString(Integer.parseInt(reorderLevel[count])* 2));
			productIds[count] = result.getProductID();
		}
	}

	public void checkoutTest() {
		// create checkout
		// add to checkout
		// complete checkout process
	}

	public void getProductInfoTest() {
		System.out.println("Testing get Product info");
		for (int count = 0; count < 3; count++) {
			Request.instance().setProductName(productsName[count]);
			Result result = GroceryStore.instance().getProductInfo(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getProductStock().equals("0");
			assert result.getProductID().equals(productIds[count]);
		}
	}

	public void processShipmentTest() {
		/*
		 * since this is order, should we do setOrderQuantity() instead?
		 * if so we have to change request.getProductStock() to request.getOrderQuantity() in GrocceryStore -> processShipment();
		 */
		System.out.println("Testing process shipment ");
		for(int count = 0; count < 3; count++) {
			Request.instance().setProductID(productIds[count]);
			Request.instance().setProductStock(Integer.toString(Integer.parseInt(reorderLevel[count])* 2));
			Result result = GroceryStore.instance().processShipment(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(newPrices[count]);
			//not sure about this one
			assert result.getProductStock().equals(Request.instance().getProductStock());
		}

	}

	public void changePriceTest() {
		System.out.println("Testing changing product prices");
		for (int count = 0; count < 3; count++) {
			Request.instance().setProductID(productIds[count]);
			Request.instance().setProductPrice(newPrices[count]);
			Result result = GroceryStore.instance().changePrice(Request.instance());

			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(newPrices[count]);
			assert result.getProductStock().equals("0");
			assert result.getProductID().equals(productIds[count]);
		}
	}

	public void printTransactionsTest() {

	}

	public void listAllMembersTest() {
		System.out.println("Testing List all members");
		Iterator<Result> results = GroceryStore.instance().listAllMembers();
		for (int count = 0; results.hasNext(); count++) {
			// will iterator return in the proper order?
			Result result = results.next();
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberAddress().equals(addresses[count]);
			assert result.getMemberPhoneNumber().equals(phones[count]);
			assert result.getMemberFeePaid().equals(Double.toString(fees[count]));
			assert result.getMemberID().equals(memberIds[count]);
		}
	}

	public void listAllProductsTest() {
		System.out.println("Testing List all Products");
		Iterator<Result> results = GroceryStore.instance().listAllProducts();
		for (int count = 0; results.hasNext(); count++) {
			// will iterator return in the proper order?
			Result result = results.next();
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getProductID().equals(productIds[count]);
		}
	}

	public void listOutstandingOrdersTest() {
		/*
		 * Not sure how to test this method, we dont create orders on our own, the
		 * system creates them.
		 * 
		 * Maybe test that the 2x reorder level orders from the adding of the products
		 * were created?
		 */
		System.out.println("Testing outstanding order");
		Iterator<Result> iterator = GroceryStore.instance().listOutstandingOrders();
		int count = 0;
		while(iterator.hasNext()) {
			Result result = iterator.next();
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductID().equals(productIds[count]);
			assert result.getOrderQuantity().equals(Integer.toString(Integer.parseInt(reorderLevel[count])* 2));
			if(count < 2) {
				count++;
			}
		}
	}

	public static void main(String[] args) {
		// runs all tests

		/*
		 * Uncomment the line commented out below to make sure you have assertions
		 * enabled in eclipse, it should throw am AssetionError exception If it does
		 * not, go to Window->Preferences->installed JRES, click the JRE you are using,
		 * click edit, and add -ea as the Default VM arguments
		 * 
		 * If you aren't using eclipse, google it I guess?
		 */

		// assert false;
		new AutomatedTester().testAll();
	}

}
