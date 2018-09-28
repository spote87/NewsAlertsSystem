/**
 * Copyright : No copyright
 */
package com.alert.news.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Shivaji Pote
 *
 */
@Setter
@Getter
public class OrganisationCampaignException extends Exception {

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	/**
	 * default constructor
	 */
	public OrganisationCampaignException() {
		super();
	}

	/**
	 * Prameterized constructor with exception message
	 * 
	 * @param message exception message
	 */
	public OrganisationCampaignException(final String message) {
		this.message = message;
	}

}
