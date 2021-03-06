package edu.ics372.gp1.facade;

import java.util.Calendar;

import edu.ics372.gp1.entities.Member;
import edu.ics372.gp1.entities.Order;
import edu.ics372.gp1.entities.Product;
import edu.ics372.gp1.entities.Transaction;

/**
 * The DataTransfer class is used to facilitate data transfer between
 * GroceryStore and UserInterface. It is also used to support iterating over
 * Entity objects. The class stores copies of fields that may be sent in either
 * direction.
 * 
 * @author Joseph Jackels
 *
 */
public class DataTransfer {
	private String memberID;
	private String memberName;
	private String memberAddress;
	private String memberPhoneNumber;
	private String memberDateJoined;
	private String memberFeePaid;

	private String productName;
	private String productID;
	private String productPrice;
	private String productReorderLevel;
	private String productStock;

	private String transactionStartDate;
	private String transactionEndDate;
	private String transactionDate;
	private String transactionTotalPrice = "";

	private String orderQuantity;

	/**
	 * Constructor sets all fields to "Invalid"
	 */
	public DataTransfer() {
		reset();
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}

	public String getMemberDateJoined() {
		return memberDateJoined;
	}

	public void setMemberDateJoined(String memberDateJoined) {
		this.memberDateJoined = memberDateJoined;
	}

	public String getMemberFeePaid() {
		return memberFeePaid;
	}

	public void setMemberFeePaid(String memberFeePaid) {
		this.memberFeePaid = memberFeePaid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductReorderLevel() {
		return productReorderLevel;
	}

	public void setProductReorderLevel(String productReorderLevel) {
		this.productReorderLevel = productReorderLevel;
	}

	public String getProductStock() {
		return productStock;
	}

	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}

	public String getTransactionStartDate() {
		return transactionStartDate;
	}

	public void setTransactionStartDate(String transactionStartDate) {
		this.transactionStartDate = transactionStartDate;
	}

	public String getTransactionEndDate() {
		return transactionEndDate;
	}

	public void setTransactionEndDate(String transactionEndDate) {
		this.transactionEndDate = transactionEndDate;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTotalPrice() {
		return transactionTotalPrice;
	}

	public void setTransactionTotalPrice(String transactionTotalPrice) {
		this.transactionTotalPrice = transactionTotalPrice;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * Given a Member, set all relevant field to those of the Member object
	 * 
	 * @param member
	 */
	public void setMemberFields(Member member) {
		memberID = member.getId();
		memberName = member.getName();
		memberAddress = member.getAddress();
		memberPhoneNumber = member.getPhoneNumber();
		memberDateJoined = member.getDateJoined().toString();
		memberFeePaid = member.getFeePaid();
	}

	/**
	 * Given a product, set all relevant fields to those of the PRoduct Object
	 * 
	 * @param product
	 */
	public void setProductFields(Product product) {
		productName = product.getName();
		productID = product.getId();
		productPrice = Double.toString(product.getPrice());
		productReorderLevel = Integer.toString(product.getReorderLevel());
		productStock = Integer.toString(product.getStock());
	}

	/**
	 * Given a transaction set all relevant fields to those of the transaction
	 * Object
	 * 
	 * @param transaction
	 */
	public void setTransactionFields(Transaction transaction) {
		memberID = transaction.getMemberID();
		transactionDate = (transaction.getDate().get(Calendar.MONTH) + 1) + "/"
				+ transaction.getDate().get(Calendar.DAY_OF_MONTH) + "/" + transaction.getDate().get(Calendar.YEAR);
		transactionTotalPrice = Double.toString(transaction.getTotalPrice());
	}

	/**
	 * Given an Order, set all relevant fields to those of the ORder object
	 * 
	 * @param order
	 */
	public void setOrderFields(Order order) {
		productID = order.getProduct().getId();
		productName = order.getProduct().getName();
		orderQuantity = Integer.toString(order.getQuantity());
	}

	/**
	 * Reset all fields
	 */
	public void reset() {
		memberID = "Invalid member ID";
		memberName = "Invalid member name";
		memberAddress = "Invalid member address";
		memberPhoneNumber = "Invalid phone number";
		memberDateJoined = "Invalid member date joined";
		memberFeePaid = "Invalid member fee paid";
		productName = "Invalid product name";
		productID = "Invalid product id";
		productPrice = "Invalid product price";
		productReorderLevel = "Invalid product reorder level";
		productStock = "Invalid product stock";
		transactionStartDate = "Invalid transaction start date";
		transactionEndDate = "Invalid transaction end date";
		transactionDate = "Invalid transaction date";
		transactionTotalPrice = "Invalid transaction total price";
		orderQuantity = "Invalid order quantity";
	}
}
