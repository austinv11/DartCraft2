package com.austinv11.dartcraft2.api;

/**
 * This exception gets thrown when a DartCraft 2 API method is called illegally
 */
public class FailedAPIRequest extends Exception {
	
	public FailedAPIRequest(String message) {
		super(message);
	}
}
