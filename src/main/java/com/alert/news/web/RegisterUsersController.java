/**
 * 
 */
package com.alert.news.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alert.news.exception.UserRegistrationException;
import com.alert.news.model.User;
import com.alert.news.service.UserRegistrationService;

/**
 * This is REST controller for registering users to different categories to get
 * news alerts.
 * 
 * @author Shivaji Pote
 *
 */
@RestController("/register")
public class RegisterUsersController {

	/**
	 * user registration service instance
	 */
	@Autowired
	private UserRegistrationService userRegistrationServiceImpl;

	/**
	 * <em>POST</em> method which will call {@link #registerUser(User)} from
	 * {@link UserRegistrationService} to register user for specifier categories
	 * news alerts.
	 * 
	 * @param user instance of {@link User} which passed by user as a part of
	 *             <em>POST</em> body
	 * @return success message
	 * @throws UserRegistrationException if something goes wrong while registering
	 *                                   user
	 */
	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody User user) throws UserRegistrationException {
		userRegistrationServiceImpl.registerUser(Optional.of(user));
		return new ResponseEntity<>("Registration successful", HttpStatus.OK);
	}
}
