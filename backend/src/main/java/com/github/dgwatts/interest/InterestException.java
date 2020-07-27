package com.github.dgwatts.interest;

public class InterestException extends RuntimeException {
	public InterestException(String message) {
		super(message);
	}

	public InterestException(String message, Throwable cause) {
		super(message, cause);
	}
}
