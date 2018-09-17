/**
 * 
 */
package com.alert.news.service.impl;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alert.news.exception.UserRegistrationException;
import com.alert.news.model.User;
import com.alert.news.repository.UserRepository;
import com.alert.news.service.UserRegistrationService;

/**
 * @author Shivaji Pote
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistrationServiceImplTest {
	
	@InjectMocks
	private UserRegistrationService userRegistrationServiceImpl = new UserRegistrationServiceImpl();
	
	@Mock
	private UserRepository userRepository;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testRegisterUserForValidUserData() throws UserRegistrationException {
		final User user = new User(9370912801L, "shivaji.pote", "Banking, Finance");
		//Mockito.when(userRepository.insert(user)).thenReturn(user);
		final Optional<User> optionalUser = Optional.of(user);
		userRegistrationServiceImpl.registerUser(optionalUser);
		Mockito.verify(userRepository, Mockito.times(1)).insert(user);
	}
	
	@Test
	public void testRegisterUserShouldThrowExceptionWhenMobileNumberIsNotProvided() throws UserRegistrationException {
		final User user = new User();
		user.setSubscriptionCategories("Finance");
		user.setUserName("Shivaji");
		final Optional<User> optionalUser = Optional.of(user);
		exception.expect(UserRegistrationException.class);
		exception.expectMessage("One of the mandatory field is empty. Please rectify the error and try again.");
		userRegistrationServiceImpl.registerUser(optionalUser);
		Mockito.verify(userRepository, Mockito.times(0)).insert(user);
	}
	
	@Test
	public void testRegisterUserShouldThrowExceptionWhenUsernameIsNotProvided() throws UserRegistrationException {
		final User user = new User();
		user.setSubscriptionCategories("Finance");
		user.setMobileNumber(9328701364L);
		final Optional<User> optionalUser = Optional.of(user);
		exception.expect(UserRegistrationException.class);
		exception.expectMessage("One of the mandatory field is empty. Please rectify the error and try again.");
		userRegistrationServiceImpl.registerUser(optionalUser);
		Mockito.verify(userRepository, Mockito.times(0)).insert(user);
	}
	
	@Test
	public void testRegisterUserShouldThrowExceptionWhenSubscriptionCategoriesIsNotProvided() throws UserRegistrationException {
		final User user = new User();
		user.setUserName("shivaji");
		user.setMobileNumber(9328701364L);
		final Optional<User> optionalUser = Optional.of(user);
		exception.expect(UserRegistrationException.class);
		exception.expectMessage("One of the mandatory field is empty. Please rectify the error and try again.");
		userRegistrationServiceImpl.registerUser(optionalUser);
		Mockito.verify(userRepository, Mockito.times(0)).insert(user);
		
	}
}
