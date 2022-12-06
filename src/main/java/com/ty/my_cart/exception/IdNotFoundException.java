package com.ty.my_cart.exception;

public class IdNotFoundException extends RuntimeException {

	String message = "Given ID doesn't exist!";

	public IdNotFoundException() {
	}

	public IdNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
