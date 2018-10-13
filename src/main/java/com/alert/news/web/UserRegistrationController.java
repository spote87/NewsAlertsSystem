package com.alert.news.web;

import com.alert.news.exception.UserException;
import com.alert.news.model.User;
import com.alert.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * This is REST controller for registering users to different categories to get
 * news alerts.
 *
 * @author Shivaji Pote
 */
@RestController
public class UserRegistrationController {

    /**
     * user registration service instance
     */
    @Autowired
    private UserService userService;

    /**
     * <em>POST</em> method which will call {@link UserService#registerUser(User)} from
     * {@link UserService} to register user for specifier categories
     * news alerts.
     *
     * @param user instance of {@link User} which passed by user as a part of
     *             <em>POST</em> body
     * @return success message wrappned in {@link ResponseEntity}
     * @throws UserException if something goes wrong while registering
     *                       user
     */
    @PostMapping("/registeruser")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) throws UserException {
        userService.registerUser(user);
        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }
}
