/**
 * 
 */
package com.alert.news.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Shivaji Pote
 *
 */
@Getter
@Setter
public class UserRegistrationException extends Exception {

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	/**
	 * default constructor
	 */
	public UserRegistrationException() {
		super();
	}

	
	/**
	 * @param message
	 */
	public UserRegistrationException(final String message) {
		super(message);
		this.message = message;
	}

}