package edu.ics372.gp1.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import edu.ics372.gp1.facade.GroceryStore;
import edu.ics372.gp1.facade.Request;

public class UserInterface {
	private static UserInterface userInterface;
	private static GroceryStore groceryStore;

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	private static final int EXIT = 0;
	private static final int ADD_MEMBER = 1;
	private static final int REMOVE_MEMBER = 2;
	private static final int GET_MEMBER_INFO = 3;
	private static final int ADD_PRODUCTS = 4;
	private static final int CHECKOUT_MEMBER = 5;
	private static final int GET_PRODUCT_INFO = 6;
	private static final int PROCESS_SHIPMENT = 7;
	private static final int CHANGE_PRICE = 8;
	private static final int PRINT_TRANSACTIONS = 9;
	private static final int LIST_ALL_MEMBERS = 10;
	private static final int LIST_ALL_PRODUCTS = 11;
	private static final int LIST_OUTSTANDING_ORDERS = 12;
	private static final int SAVE = 13;
	private static final int RETRIEVE = 14;
	private static final int HELP = 15;

	private UserInterface() {
		if (yesOrNo("Looke for saved data and use it?")) {
			retrieve();
		} else {
			groceryStore = groceryStore.instance();
		}
	}

	public static UserInterface instance() {
		if (userInterface == null) {
			userInterface = new UserInterface();
		}
		return userInterface;
	}

	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);

	}

	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	public double getDouble(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a double ");
			}
		} while (true);
	}

	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	public void help() {
		System.out.println("Enter a number between 0 and 15 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_MEMBER + " to add a member");
		System.out.println(REMOVE_MEMBER + " to remove a member");
		System.out.println(GET_MEMBER_INFO + " to  print info about a given member");
		System.out.println(ADD_PRODUCTS + " to add new products");
		System.out.println(CHECKOUT_MEMBER + " to checkout a member's cart");
		System.out.println(GET_PRODUCT_INFO + " to retrieve information about a product");
		System.out.println(PROCESS_SHIPMENT + " to process a received shipment of a product");
		System.out.println(CHANGE_PRICE + " to change the price of a product");
		System.out.println(PRINT_TRANSACTIONS + " to print transactions for a member on/between two dates");
		System.out.println(LIST_ALL_MEMBERS + " to print name, id, and address of each member");
		System.out.println(LIST_ALL_PRODUCTS + " to  print name, id, price, and reorder level for each product");
		System.out.println(LIST_OUTSTANDING_ORDERS + " to print each order that has not been receieved yet");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	public void process() {
		int command;
		help();

		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case HELP:
				help();
				break;
			case ADD_MEMBER:
				addMember();
				break;
			case REMOVE_MEMBER:
				removeMember();
				break;
			case GET_MEMBER_INFO:
				getMemberInfo();
				break;
			case ADD_PRODUCTS:
				addProducts();
				break;
			case CHECKOUT_MEMBER:
				checkoutMember();
				break;
			case GET_PRODUCT_INFO:
				getProductInfo();
				break;
			case PROCESS_SHIPMENT:
				processShipment();
				break;
			case CHANGE_PRICE:
				changePrice();
				break;
			case PRINT_TRANSACTIONS:
				printTransactions();
				break;
			case LIST_ALL_MEMBERS:
				listAllMembers();
				break;
			case LIST_ALL_PRODUCTS:
				listAllProducts();
				break;
			case LIST_OUTSTANDING_ORDERS:
				listOutstandingOrders();
				break;
			case SAVE:
				save();
				break;
			default:
				unkownCommand();
				break;
			}
		}
	}

	public void addMember() {
		Request.instance().setMemberName(getName("Enter a name:"));
		Request.instance().setMemberAddress(null);
		Request.instance().setMemberPhoneNumber(null);
		Request.instance().setMemberFeePaid(null);
	}

	public void removeMember() {

	}

	public void getMemberInfo() {

	}

	public void addProducts() {

	}

	public void checkoutMember() {

	}

	public void getProductInfo() {

	}

	public void processShipment() {

	}

	public void changePrice() {

	}

	public void printTransactions() {

	}

	public void listAllMembers() {

	}

	public void listAllProducts() {

	}

	public void listOutstandingOrders() {

	}

	public void save() {

	}

	public void retrieve() {

	}

	public void unkownCommand() {

	}

	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}
