package edu.ics372.gp1.facade;

public class Result extends DataTransfer {
	public static final int OPERATION_COMPLETED = 0;
	public static final int OPERATION_FAILED = 1;
	public static final int MEMBER_NOT_FOUND = 2;
	public static final int PRODUCT_NAME_INVALID = 3;
	public static final int PRODUCT_NOT_FOUND = 4;
	public static final int PRODUCT_OUT_OF_STOCK = 5;
	public static final int PRODUCT_REORDERED = 6;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
