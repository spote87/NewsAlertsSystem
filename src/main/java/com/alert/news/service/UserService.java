package com.alert.news.service;

import java.util.List;

import com.alert.news.exception.UserException;
import com.alert.news.model.User;
import com.alert.news.repository.UserRepository;

/**
 * User registration service interface.
 *
 * @author Shivaji Pote
 */
public interface UserService {

    /**
     * This method subscribes user for specified categories. It inserts user details
     * into database.
     *
     * @param user {@link User} instance which contains user details
     *                     including categories interested in
     * @throws UserException if something goes wrong while
     *                inserting user data into database
     */
    void registerUser(final User user) throws UserException;

    /**
     * This method calls {@link UserRepository#findAll()} to retrieve all users data from database.
     *
     * @return {@link List} users
     */
    List<User> getAllUsers();
}
