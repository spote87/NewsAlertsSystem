package com.alert.news.service.impl;

import com.alert.news.TestConfiguration;
import com.alert.news.exception.UserException;
import com.alert.news.model.User;
import com.alert.news.repository.UserRepository;
import com.alert.news.service.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

/**
 * @author Shivaji Pote
 */
public class UserServiceImplTest extends TestConfiguration {

    @InjectMocks
    private UserService userServiceImpl = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testRegisterUserForValidUserData() throws UserException {
        final User user = new User(9370912801L, "shivaji.pote", Arrays.asList("Banking", "Finance"));
        userServiceImpl.registerUser(user);
        Mockito.verify(userRepository, Mockito.times(1)).insert(user);
    }

    @Test
    public void testRegisterUserShouldThrowExceptionWhenMobileNumberIsNotProvided() throws UserException {
        final User user = new User();
        user.setSubscriptionCategories(Arrays.asList("Finance", "LIC"));
        user.setUserName("Shivaji");
        exception.expect(UserException.class);
        exception.expectMessage("One of the mandatory field is empty. Please rectify the error and try again.");
        userServiceImpl.registerUser(user);
        Mockito.verify(userRepository, Mockito.times(0)).insert(user);
    }

    @Test
    public void testRegisterUserShouldThrowExceptionWhenUsernameIsNotProvided() throws UserException {
        final User user = new User();
        user.setSubscriptionCategories(Arrays.asList("Finance", "LIC"));
        user.setMobileNumber(9328701364L);
        exception.expect(UserException.class);
        exception.expectMessage("One of the mandatory field is empty. Please rectify the error and try again.");
        userServiceImpl.registerUser(user);
        Mockito.verify(userRepository, Mockito.times(0)).insert(user);
    }

    @Test
    public void testRegisterUserShouldThrowExceptionWhenSubscriptionCategoriesIsNotProvided() throws UserException {
        final User user = new User();
        user.setUserName("shivaji");
        user.setMobileNumber(9328701364L);
        exception.expect(UserException.class);
        exception.expectMessage("One of the mandatory field is empty. Please rectify the error and try again.");
        userServiceImpl.registerUser(user);
        Mockito.verify(userRepository, Mockito.times(0)).insert(user);

    }
}
