/**
 * 
 */
package com.alert.news.service;

import java.util.Optional;

import com.alert.news.exception.UserRegistrationException;
import com.alert.news.model.User;

/**
 * User registration service interface.
 * 
 * @author Shivaji Pote
 *
 */
public interface UserRegistrationService {

	/**
	 * This method subscribes user for specified categories. It inserts user details
	 * into database.
	 * 
	 * @param optionalUser {@link User} instance which contains user details
	 *                     including categories interested in
	 * @throws {@link UserRegistrationException} if something goes wrong while
	 *         inserting user data into database
	 */
	void registerUser(final Optional<User> optionalUser) throws UserRegistrationException;

}
