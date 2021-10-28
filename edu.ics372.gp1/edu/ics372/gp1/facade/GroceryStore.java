package edu.ics372.gp1.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.collections.MemberList;
import edu.ics372.gp1.collections.OrderList;
import edu.ics372.gp1.collections.ProductList;
import edu.ics372.gp1.collections.TransactionList;
import edu.ics372.gp1.entities.Member;
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

		// Attempt to add member to member list
		Member member = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhoneNumber(),
				Double.parseDouble(request.getMemberFeePaid()));
		if (members.insertMember(member)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}

		result.setMemberFields(member);

		return result;
	}

	public Result removeMember(Request request) {
		Result result = new Result();

		// TODO
		// Attempt to remove member
		result.setMemberID(request.getMemberID());
		if (!members.isMember(request.getMemberID())) {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
		} else if (members.removeMember(request.getMemberID())) {
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		// set member fields to that of the removed member instead?
		// would have to have MemberList.removeMember() return a member instead of a
		// boolean
		// pro - would be easy to return information about the member that was removed

		return result;
	}

	public Iterator<Result> getMemberInfo(Request request) {
		List<Result> resultList = new LinkedList<Result>();

		// get list of all members with name entered in request
		Iterator<Member> memberIterator = members.getMembers();
		while (memberIterator.hasNext()) {
			Member member = memberIterator.next();
			if (member.getName().equals(request.getMemberName())) {
				Result result = new Result();
				result.setMemberFields(member);
				result.setResultCode(Result.OPERATION_COMPLETED);
				resultList.add(result);
			}
		}

		return resultList.iterator();
	}

	public Result addProduct(Request request) {

		Result result = new Result();
		result.setProductName(request.getProductName());
		Product product = new Product(request.getProductName(), Integer.parseInt(request.getProductReorderLevel()), 0,
				Double.parseDouble(request.getProductPrice()));
		result.setProductFields(product);

		// attempt to add product
		// check if name is taken
		if (!products.nameAvailable(request.getProductName())) {
			result.setResultCode(Result.PRODUCT_NAME_INVALID);
		} else if (products.insertProduct(product)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			result.setResultCode(Result.OPERATION_FAILED);
		}

		return result;
	}

	public Result createNewCheckout(Request request) {
		Result result = new Result();
		// verify member exists

		// reset groceryStore's checkoutList object
		result.setResultCode(Result.OPERATION_COMPLETED);
		checkOutList = new LinkedList<Product>();

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

	public Iterator<Result> completeCheckout(Request request) {
		List<Result> resultList = new LinkedList<Result>();

		// TODO
		// actor has finished adding products to checkoutList
		// ensure list is not empty
		// create transaction and add to transaction list
		// check reorder level for each product checked out
		// if product is reordered make sure to set result code for that product result
		// to PRODUCT_REORDERED
		// else set result code based on success

		return resultList.iterator();
	}

	public Result getProductInfo(Request request) {
		Result result = new Result();

		// TODO
		// searchf or product by name
		// if not found set resultCode to PRODUCT_NOT_FOUND
		// else set product fields in result and set result code to OPERATION_COMPLETED

		return result;
	}

	public Result processShipment(Request request) {
		Result result = new Result();

		// TODO
		// check that product id exists
		// if nto set result code to PRODUCT_NOT_FOUND
		// check if exists in orderList
		// if not set result code to ORDER_NOT_FOUND
		// check that quantity received matches order quantity
		// if nto set result code to INCORRECT_RECIEVED_QUANTITY
		// update product stock in productList
		// set result code to OPERATION_COMPLETED
		// set all product fields in result
		// REMOVE FROM ORDER LIST

		return result;
	}

	public Result changePrice(Request request) {
		Result result = new Result();

		// TODO
		// check that product id exists
		// if not set result code to PRODUCT_NOT_FOUND
		// update price in productList
		// set result code to OPERATION_COMPLETED
		// set all product fields in result

		return result;
	}

	public Iterator<Result> printTransactions(Request request) {
		List<Result> resultList = new LinkedList<Result>();

		// TODO
		// verify member id, if not found create a single result and
		// set its result code to MEMBER_NOT_FOUND
		// verify dates are valid options (start date occurs on or before end date)
		// if not, create single result and set result code to INVALID_DATES
		// else retrieve list opf transactions, create a result for each transaction,
		// set all
		// relevent fields, ands et result code to OPERATION_COMPLETED

		return resultList.iterator();
	}

	public Iterator<Result> listAllMembers() {
		List<Result> resultList = new LinkedList<Result>();

		// create a list of results corresponding
		// to each entry in memberList

		return resultList.iterator();
	}

	public Iterator<Result> listAllProducts() {
		List<Result> resultList = new LinkedList<Result>();

		// create a list of results corresponding to each entry
		// in product list

		return resultList.iterator();
	}

	public Iterator<Result> listOutstandingOrders() {
		List<Result> resultList = new LinkedList<Result>();

		// create a list of results corresponding
		// to each entry in orders

		return resultList.iterator();
	}

	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(groceryStore);
			// Member.save(output);
			// TODO
			// save anything else???
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			groceryStore = (GroceryStore) input.readObject();
			// Member.retrieve(input);
			// TODO
			// retrieve anything that needs to be???
			return groceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
}
