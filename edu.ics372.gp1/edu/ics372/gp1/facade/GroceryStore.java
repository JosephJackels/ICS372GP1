package edu.ics372.gp1.facade;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.collections.MemberList;
import edu.ics372.gp1.collections.OrderList;
import edu.ics372.gp1.collections.ProductList;
import edu.ics372.gp1.collections.TransactionList;
import edu.ics372.gp1.entities.Product;

public class GroceryStore {
	private MemberList members = MemberList.getInstance();
	private OrderList orders = OrderList.getInstance();
	private ProductList products = ProductList.getInstance();
	private TransactionList transactions = TransactionList.getInstance();
	private List<Product> checkOutList = new LinkedList<Product>();

	private static GroceryStore groceryStore;

	private GroceryStore() {

	}

	public static GroceryStore instance() {
		if (groceryStore == null) {
			groceryStore = new GroceryStore();
		}

		return groceryStore;
	}

	public Result addMember(Request request) {
		Result result = new Result();
		// TODO
		// Attempt to add member to member list
		// set result code based on success (OPERATION_COMPLETE, OPERATION_FAILED)
		// if successful, set all member fields to those of the created member

		return result;
	}

	public Result removeMember(Request request) {
		Result result = new Result();

		// TODO
		// Attempt to remove member
		// set result code (OPERATION_COMPLETE, MEMBER_NOT_FOUND)
		// set member fields to that of the removed member

		return result;
	}

	public Iterator<Result> getMemberInfo(Request request) {
		List<Result> resultList = new LinkedList<Result>();

		// TODO
		// get list of all members with name entered in request
		// for each, create result object, add to resultList
		// set codes and fields etc.

		return resultList.iterator();
	}

	public Result addProduct(Request request) {

		Result result = new Result();

		// TODO
		// attempt to add product
		// check if name is taken
		// if it is, set result code to PRODUCT_NAME_INVALID
		// else attempt to add product to product list
		// set result code based on success, return

		return result;
	}

	public Result createNewCheckout(Request request) {
		Result result = new Result();

		// TODO
		// reset groceryStore's checkoutList object
		// return successful result

		return result;
	}

	public Result addProductToCheckout(Request request) {
		Result result = new Result();

		// TODO
		// add a single product to groceryStores checkoutList object
		// this product's stock field will be reused as quantity to checkout
		// check that product exists in productList AND contains enough stock
		// set result code (PRODUCT_NOT_FOUND, PRODUCT_OUT_OF_STOCK,
		// OPERATION_COMPLETED)

		return result;
	}

	public Result completeCheckout(Request request) {
		Result result = new Result();

		// TODO
		// actor has finished adding products to checkoutList
		// ensure list is not empty
		// create transaction and add to transaction list
		// set result code based on success

		return result;
	}
}
