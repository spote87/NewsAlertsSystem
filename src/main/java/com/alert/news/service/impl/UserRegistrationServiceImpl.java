/**
 * 
 */
package com.alert.news.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alert.news.exception.UserRegistrationException;
import com.alert.news.model.User;
import com.alert.news.repository.UserRepository;
import com.alert.news.service.UserRegistrationService;

import lombok.extern.slf4j.Slf4j;

/**
 * This is user registration service class.
 * 
 * @author Shivaji Pote
 *
 */
@Slf4j
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	/**
	 * Error message for missing mandatory field/s
	 */
	private static final String MSG_MANDATORY_FIELD_MISSING = "One of the mandatory field is empty. Please rectify the error and try again.";

	/**
	 * user repository instance
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerUser(final Optional<User> optionalUser) throws UserRegistrationException {
		if (optionalUser.isPresent() && isValidUser(optionalUser.get())) {
			final User user = optionalUser.get();
			userRepository.insert(user);
		} else {
			final String message = MSG_MANDATORY_FIELD_MISSING;
			log.error(message);
			throw new UserRegistrationException(message);
		}
	}

	/**
	 * Checks if all mandatory fields are present or not.
	 * 
	 * @param user instance of {@link User}
	 * @return true if all mandatory fields are present, false otherwise
	 */
	private boolean isValidUser(final User user) {
		return (user.getMobileNumber() != null && user.getUserName() != null
				&& user.getSubscriptionCategories() != null);
	}

}
