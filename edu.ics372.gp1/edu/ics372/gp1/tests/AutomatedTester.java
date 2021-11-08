package edu.ics372.gp1.tests;

import java.util.Calendar;
import java.util.Iterator;

import edu.ics372.gp1.facade.GroceryStore;
import edu.ics372.gp1.facade.Request;
import edu.ics372.gp1.facade.Result;

/**
 * this class generate a sample test for the Grocery Store using assert.
 * 
 * @author Joseph Jackels, Andy Phan, Dilli Khatiwoda, Leonardo Lewis, Austin
 *         Wang
 *
 */
public class AutomatedTester {
	private GroceryStore groceryStore;
	private String[] names = { "n1", "n2", "n3", "n4", "n5" };
	private String[] addresses = { "a1", "a2", "a3", "a4", "a5" };
	private String[] phones = { "p1", "p2", "p3", "p4", "p5" };
	private double[] fees = { 1.0, 2.0, 3.0, 4.0, 5.0 };
	// private Member[] members = new Member[5];
	private String[] memberIds = { "", "", "", "", "" };

	private String[] productIds = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
	private String[] productsName = { "Apple", "Pear", "Orange", "Banana", "Kiwi", "Cheese", "Pie", "Soda", "Bacon",
			"Milk", "Bread", "Ham", "Juice", "Eggs", "Chicken", "Tomatoes", "Grapes", "Melon", "Pizza", "Fries" };
	private String[] price = { "1.55", "2.52", "3.57", "10.01", "1.01", "4.31", "1.81", "9.95", "17.77", "1.05", "6.25",
			"7.25", "8.45", "3.31", "1.35", "9.99", "10.05", "5.52", "7.75", "5.53" };
	private String[] newPrices = { "5.24", "1.02", "6.74", "10.01", "19.52", "9.99", "6.45", "2.59", "3.29", "7.79",
			"5.59", "7.64", "4.43", "3.25", "9.99", "8.51", "7.54", "8.83", "6.65", "1.05" };
	private String[] reorderLevel = { "10", "11", "12", "3", "100", "50", "5", "17", "150", "42", "99", "183", "41",
			"7", "40", "15", "55", "75", "30", "19" };

	private String transactionTotalPrice;

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

		
		listOutstandingOrdersTest();
		processShipmentTest();

		
		checkoutTest();
		printTransactionsTest();

		removeMembersTest();
	}

	/**
	 * test member creation
	 */
	public void addMembersTest() {
		System.out.println("Testing add members");
		for (int count = 0; count < memberIds.length; count++) {
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
			memberIds[count] = result.getMemberID();
		}
	}

	/**
	 * Test removing Member objects
	 */
	public void removeMembersTest() {
		System.out.println("Testing remove member");
		for (int count = 0; count < 1; count++) {
			Request.instance().setMemberID(memberIds[count]);
			Result result = GroceryStore.instance().removeMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberAddress().equals(addresses[count]);
			assert result.getMemberPhoneNumber().equals(phones[count]);
			assert result.getMemberFeePaid().equals(Double.toString(fees[count]));
		}
	}

	/**
	 * Test getting information of Member objects by name
	 */
	public void getMemberInfoTest() {
		System.out.println("Testing get member(s) by name");
		for (int count = 0; count < memberIds.length; count++) {
			Request.instance().setMemberName(names[count]);
			Iterator<Result> results = GroceryStore.instance().getMemberInfo(Request.instance());
			while (results.hasNext()) {
				Result result = results.next();
				assert result.getMemberName().equals(names[count]);
			}
		}
	}

	/**
	 * Test adding products to the GroceryStore
	 */
	public void addProductTest() {
		System.out.println("Testing add Product");
		for (int count = 0; count < productIds.length; count++) {
			Request.instance().setProductName(productsName[count]);
			Request.instance().setProductReorderLevel(reorderLevel[count]);
			Request.instance().setProductPrice(price[count]);
			Result result = GroceryStore.instance().addProduct(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getOrderQuantity().equals(Integer.toString(Integer.parseInt(reorderLevel[count]) * 2));
			productIds[count] = result.getProductID();
		}
	}

	/**
	 * test Checkout member's cart
	 */
	public void checkoutTest() {
		System.out.println("Testing checkout");
		for (int i = 0; i < 3; i++) {
			Request.instance().setMemberID(memberIds[i]);
			Result result = GroceryStore.instance().createNewCheckout(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			int count;
			for (count = 0; count < 3; count++) {
				Request.instance().setProductID(productIds[count]);
				Request.instance().setProductStock("5");
				Result checkOutResult = GroceryStore.instance().addProductToCheckout(Request.instance());
				assert checkOutResult.getProductID().equals(productIds[count]);
				assert checkOutResult.getProductName().equals(productsName[count]);
				assert checkOutResult.getProductStock().equals(Request.instance().getProductStock());
			}
			count = 0;
			Iterator<Result> iterator = GroceryStore.instance().completeCheckout(Request.instance());
			while (iterator.hasNext()) {
				Result results = iterator.next();
				if (results.getResultCode() == Result.OPERATION_COMPLETED) {
					assert results.getResultCode() == Result.OPERATION_COMPLETED;
				} else if (results.getResultCode() == Result.PRODUCT_REORDERED) {
					assert (results.getResultCode() == Result.PRODUCT_REORDERED);
				} else if (results.getResultCode() == Result.PRODUCT_ALREADY_ORDERED) {
					assert (results.getResultCode() == Result.PRODUCT_ALREADY_ORDERED);
				}
				assert results.getMemberID().equals(Request.instance().getMemberID());
				assert results.getProductID().equals(productIds[count]);
				assert results.getProductName().equals(productsName[count]);
				if (results.getTransactionTotalPrice() != null) {
					transactionTotalPrice = results.getTransactionTotalPrice();
				}
				count++;
			}
		}
	}

	/**
	 * Test getting Product information by name
	 */
	public void getProductInfoTest() {
		System.out.println("Testing get Product info");
		for (int count = 0; count < productIds.length; count++) {
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

	/**
	 * Test processing and completing Orders
	 */
	public void processShipmentTest() {
		System.out.println("Testing process shipment ");
		for (int count = 0; count < productIds.length; count++) {
			Request.instance().setProductID(productIds[count]);
			Request.instance().setProductStock(Integer.toString(Integer.parseInt(reorderLevel[count]) * 2));
			Result result = GroceryStore.instance().processShipment(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(newPrices[count]);
			assert result.getProductStock().equals(Request.instance().getProductStock());
		}

	}

	/**
	 * test Changing price of a product.
	 */
	public void changePriceTest() {
		System.out.println("Testing changing product prices");
		for (int count = 0; count < productIds.length; count++) {
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

	/**
	 * Test getting transactions by (memberId, startDate, endDate)
	 */
	public void printTransactionsTest() {
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = (Calendar) startDate.clone();
		endDate.add(Calendar.DAY_OF_MONTH, 1);
		Request.instance().setMemberID(memberIds[0]);
		Request.instance().setStartDate(startDate);
		Request.instance().setEndDate(endDate);
		Iterator<Result> iterator = GroceryStore.instance().printTransactions(Request.instance());
		System.out.println("Testing print Transaction");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			assert result.getMemberID().equals(Request.instance().getMemberID());
			assert result.getTransactionTotalPrice().equals(transactionTotalPrice);
		}
	}

	/**
	 * Test listing all Members
	 */
	public void listAllMembersTest() {
		System.out.println("Testing List all members");
		Iterator<Result> results = GroceryStore.instance().listAllMembers();
		for (int count = 0; results.hasNext(); count++) {
			Result result = results.next();
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberAddress().equals(addresses[count]);
			assert result.getMemberPhoneNumber().equals(phones[count]);
			assert result.getMemberFeePaid().equals(Double.toString(fees[count]));
			assert result.getMemberID().equals(memberIds[count]);
		}
	}

	/**
	 * Test listing all products
	 */
	public void listAllProductsTest() {
		System.out.println("Testing List all Products");
		Iterator<Result> results = GroceryStore.instance().listAllProducts();
		for (int count = 0; results.hasNext(); count++) {
			Result result = results.next();
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductReorderLevel().equals(reorderLevel[count]);
			assert result.getProductPrice().equals(price[count]);
			assert result.getProductID().equals(productIds[count]);
		}
	}

	/**
	 * Test listing all orders
	 */
	public void listOutstandingOrdersTest() {
		System.out.println("Testing outstanding order");
		Iterator<Result> iterator = GroceryStore.instance().listOutstandingOrders();
		int count = 0;
		while (iterator.hasNext()) {
			Result result = iterator.next();
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productsName[count]);
			assert result.getProductID().equals(productIds[count]);
			assert result.getOrderQuantity().equals(Integer.toString(Integer.parseInt(reorderLevel[count]) * 2));
			count++;
		}
	}

	public static void main(String[] args) {
		// runs all tests

		/*
		 * Uncomment the line commented out below to make sure you have assertions
		 * enabled in eclipse, it should throw am AssetionError exception If it does
		 * not, go to Window->Preferences->installed JRES, click the JRE you are using,
		 * click edit, and add -ea as the Default VM arguments
		 */

		// assert false;
		new AutomatedTester().testAll();
	}

}
