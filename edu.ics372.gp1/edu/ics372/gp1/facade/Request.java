package edu.ics372.gp1.facade;

public class Request extends DataTransfer {
	private static Request request;

	private Request() {
		super();
	}

	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}
}
