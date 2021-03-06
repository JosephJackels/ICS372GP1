package edu.ics372.gp1.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.collections.MemberList;
import edu.ics372.gp1.collections.OrderList;
import edu.ics372.gp1.collections.ProductList;
import edu.ics372.gp1.collections.TransactionList;
import edu.ics372.gp1.entities.Member;
import edu.ics372.gp1.entities.Order;
import edu.ics372.gp1.entities.Product;
import edu.ics372.gp1.entities.Transaction;
import edu.ics372.gp1.iterators.SafeMemberIterator;
import edu.ics372.gp1.iterators.SafeOrderIterator;
import edu.ics372.gp1.iterators.SafeProductIterator;
import edu.ics372.gp1.iterators.SafeTransactionIterator;

/**
 * The facade class handling all requests from users.
 * 
 * @author Joseph Jackels, Andy Phan, Dilli Khatiwoda, Leonardo Lewis, Austin
 *         Wang
 *
 */
public class GroceryStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private MemberList members = MemberList.getInstance();
	private OrderList orders = OrderList.getInstance();
	private ProductList products = ProductList.getInstance();
	private TransactionList transactions = TransactionList.getInstance();
	private List<Product> checkOutList = new LinkedList<Product>();
	private static GroceryStore groceryStore;

	/**
	 * Private constructor for the singleton pattern Creates the catalog and member
	 * collection objects
	 */
	private GroceryStore() {

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static GroceryStore instance() {
		if (groceryStore == null) {
			groceryStore = new GroceryStore();
		}

		return groceryStore;
	}

	/**
	 * Organizes the operations for adding a member
	 * 
	 * @param name    member name
	 * @param address member address
	 * @param phone   member phone
	 * @return the result of Member object created
	 */
	public Result addMember(Request request) {
		Result result = new Result();
		Member member = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhoneNumber(),
				request.getMemberFeePaid());
		if (members.insertMember(member)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(member);
			return result;
		}
		
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Removes a specific member from the member list
	 * 
	 * @param memberId
	 * @return the result of removing a member
	 */
	public Result removeMember(Request request) {
		Result result = new Result();
		Member member = members.getMember(request.getMemberID());
		if (member ==  null) {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			return result;
		}
		
		if (members.removeMember(request.getMemberID())) {
			result.setMemberFields(member);
			result.setResultCode(Result.OPERATION_COMPLETED);
			return result;
		}

		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Returns an iterator to all members with specific name
	 * 
	 * @param Member name
	 * @return iterator to the Result objects storing info about members
	 */
	public Iterator<Result> getMemberInfo(Request request) {
		return new SafeMemberIterator(members.getMembersByName(request.getMemberName()));
	}

	/**
	 * Organizes the operations for adding a product
	 * 

	 * @param name of product
	 * @param product reorder level
	 * @param product price
	 * @return the result of Product object created
	 */
	public Result addProduct(Request request) {
		Result result = new Result();
    
		if(!products.nameAvailable(request.getProductName())) {
			result.setProductName(request.getProductName());
			result.setResultCode(Result.PRODUCT_NAME_INVALID);
			return result;
		}
		
		Product product = new Product(request.getProductName(), Integer.parseInt(request.getProductReorderLevel()), 0,
				Double.parseDouble(request.getProductPrice()));

		if (products.insertProduct(product)) {
			Order order = new Order(product, product.getReorderLevel() * 2);
			result.setResultCode(Result.OPERATION_COMPLETED);
			orders.addOrder(order);
			result.setOrderFields(order);
			result.setProductFields(product);
			return result;
		}
    
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Organizes the operations for verifying and creating a new checkout list
	 * 
	 * @param Member ID
	 * @return the result of creating a new checkOutList
	 */
	public Result createNewCheckout(Request request) {
		Result result = new Result();
		checkOutList = new LinkedList<Product>();

		result.setMemberID(request.getMemberID());
		if (members.isMember(request.getMemberID())) {
			result.setResultCode(Result.OPERATION_COMPLETED);
		} else {
			result.setResultCode(Result.MEMBER_NOT_FOUND);
		}

		return result;
	}
	
	
	/**
	 * Organizes the operations for adding a single product to the checkout list.
	 * 
	 * @param Product ID
	 * @return the result of adding a product
	 */
	public Result addProductToCheckout(Request request) {
		Result result = new Result();
		Product product = products.getProductById(request.getProductID());
		result.setProductID(request.getProductID());
		result.setProductStock(request.getProductStock());
		if (!products.isProduct(request.getProductID())) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
		} else if (!products.hasStock(request.getProductID(), Integer.parseInt(request.getProductStock()))) {
			result.setResultCode(Result.PRODUCT_OUT_OF_STOCK);
		} else {
			result.setProductFields(product);
			result.setProductStock(request.getProductStock());
			result.setResultCode(Result.OPERATION_COMPLETED);

			product = new Product(product.getName(), product.getReorderLevel(),
					Integer.parseInt(result.getProductStock()), product.getPrice());
			product.setId(result.getProductID());
			checkOutList.add(product);
		}
		return result;
	}

	/**
	 * Organizes the operations for completing the checkout. 
	 * The total is computed, products are reordered, and transaction is recorded.
	 *  
	 * @param Member Id
	 * @return list of processed products
	 */
	public Iterator<Result> completeCheckout(Request request) {
		List<Result> resultList = new LinkedList<Result>();
		double total = 0;
		if (!checkOutList.isEmpty()) {
			Iterator<Product> iterator = checkOutList.listIterator();
			while (iterator.hasNext()) {
				Result result = new Result();
				Product checkOutProduct = iterator.next();
				Product product = products.getProductById(checkOutProduct.getId());
				product.setStock(product.getStock() - checkOutProduct.getStock());
				if (product.getStock() <= product.getReorderLevel()) {
					if (orders.search(product.getId()) == null) {
						if (orders.addOrder(new Order(product, product.getReorderLevel() * 2))) {
							result.setResultCode(Result.PRODUCT_REORDERED);
						} else {
							result.setResultCode(Result.OPERATION_FAILED);
						}
					} else {
						result.setResultCode(Result.PRODUCT_ALREADY_ORDERED);
					}

				}
				total += checkOutProduct.getStock() * checkOutProduct.getPrice();
				result.setProductFields(checkOutProduct);
				result.setMemberID(request.getMemberID());
				resultList.add(result);
			}
			Transaction transaction = new Transaction(request.getMemberID(), total);
			transactions.insertTransaction(transaction);
			resultList.get(resultList.size() - 1).setTransactionFields(transaction);
		}
		return resultList.iterator();
	}

	/**
	 * Organizes the operations to search for a product by name
	 * @param Product name
	 * @return product
	 */
	public Result getProductInfo(Request request) {
		Result result = new Result();

		Product product = products.getProductByName(request.getProductName());
		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			result.setProductName(request.getProductName());
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		}
		return result;
	}

	/**
	 * Organizes the operations for processing an incoming shipment
	 *  
	 * @param Product Id
	 * @return the result of shipment processed
	 */
	public Result processShipment(Request request) {
		Result result = new Result();
		Product product = products.getProductById(request.getProductID());
		result.setProductID(request.getProductID());
		result.setOrderQuantity(request.getProductStock());

		Order order;
		int quantity = Integer.parseInt(request.getProductStock());
		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		} else {
			order = orders.search(product.getId());
			if (order == null) {
				result.setResultCode(Result.ORDER_NOT_FOUND);
				return result;
			} else if (quantity != order.getQuantity()) {
				result.setProductReorderLevel(Integer.toString(product.getReorderLevel()));
				result.setResultCode(Result.INCORRECT_RECEIVED_QUANTITY);
				return result;
			}
		}

		product.addStock(order.getQuantity());
		result.setResultCode(Result.OPERATION_COMPLETED);
		orders.removeOrder(product.getId());
		result.setProductFields(product);

		return result;
	}

	/**
	 * Organizes the operations for changing the price of a given product
	 *
	 * @param Product ID
	 * @return the result of changing the price
	 */
	public Result changePrice(Request request) {
		Result result = new Result();
		Product product = products.getProductById(request.getProductID());
		if (product == null) {
			result.setProductID(request.getProductID());
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
		
		product.setPrice(Double.parseDouble(request.getProductPrice()));
		result.setProductName(product.getName());
		result.setResultCode(Result.OPERATION_COMPLETED);
		result.setProductFields(product);

		return result;
	}

	/**
	 * Organizes the operations to print the transactions of a member between two dates.
	 *  
	 * @param member is, start date, end date
	 * @return list of transactions
	 */
	public Iterator<Result> printTransactions(Request request) {
		List<Result> resultList = new LinkedList<Result>();

		Member member = members.getMember(request.getMemberID());
		if (member == null) {
			Result result = new Result();
			result.setMemberID(request.getMemberID());
			result.setResultCode(Result.MEMBER_NOT_FOUND);
			resultList.add(result);
			return resultList.iterator();
		}

		Calendar startDate = request.getStartDate();
		Calendar endDate = request.getEndDate();
		if (startDate.compareTo(endDate) > 0) {
			Result result = new Result();
			result.setMemberID(request.getMemberID());
			result.setResultCode(Result.INVALID_DATES);
			resultList.add(result);
			return resultList.iterator();
		}

		return new SafeTransactionIterator(transactions.getTransactions(request.getMemberID(), startDate, endDate));
	}
	
	/**
	 * Returns an iterator to Member info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Member fields are assembled into the
	 * objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Member fields are valid.
	 */
	public Iterator<Result> listAllMembers() {
		return new SafeMemberIterator(members.getMembers());
	}

	/**
	 * Returns an iterator to Product info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Member fields are assembled into the
	 * objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Product fields are valid.
	 */
	public Iterator<Result> listAllProducts() {
		return new SafeProductIterator(products.getIterator());
	}

	/**
	 * Returns an iterator to Order info. The Iterator returned is a safe one, in
	 * the sense that only copies of the Member fields are assembled into the
	 * objects returned via next().
	 * 
	 * @return an Iterator to Result - only the Order fields are valid.
	 */
	public Iterator<Result> listOutstandingOrders() {
		return new SafeOrderIterator(orders.iterator());
	}

	/**
	 * Serializes the Library object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(groceryStore);
			Member.save(output);
			Order.save(output);
			Product.save(output);
			output.close();
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a deserialized version of the library from disk
	 * 
	 * @return a Library object
	 */
	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			groceryStore = (GroceryStore) input.readObject();
			Member.retrieve(input);
			Order.retrieve(input);
			Product.retrieve(input);
			input.close();
			file.close();
			return groceryStore;
		} catch (IOException ioe) {
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
}
