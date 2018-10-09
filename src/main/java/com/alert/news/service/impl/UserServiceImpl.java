package com.alert.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alert.news.exception.UserException;
import com.alert.news.model.User;
import com.alert.news.repository.UserRepository;
import com.alert.news.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * This is user registration service class.
 *
 * @author Shivaji Pote
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

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
     * @param user
     */
    @Override
    public void registerUser(final User user) throws UserException {
        Assert.notNull(user, "User cannot be null");
        if (isValidUser(user)) {
            userRepository.insert(user);
        } else {
            final String message = MSG_MANDATORY_FIELD_MISSING;
            log.error(message);
            throw new UserException(message);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Checks if all mandatory fields are present or not.
     *
     * @param user instance of {@link User}
     * @return true if all mandatory fields are present, false otherwise
     */
    private boolean isValidUser(final User user) {
        return (user.getMobileNumber() != null && !StringUtils.isEmpty(user.getUserName())
                && !StringUtils.isEmpty(user.getSubscriptionCategories()));
    }

}
