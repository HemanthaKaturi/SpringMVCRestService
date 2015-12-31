package com.spring.webservice.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.webservice.model.User;
import com.spring.webservice.service.IUserService;
import com.spring.webservice.test.util.TestUtils;

/**
 * Junit class for rest services in UserRestController.class
 * 
 * @author Hemantha
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "/com/spring/context/test-context.xml" })
public class UserRestControllerTest {
	private static final String BASE_URI = "/user/";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private IUserService userService;

	@Before
	public void setUp() {
		reset(userService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		List<User> userList = new ArrayList<>();
		addUsers(userList);
		when(userService.getUsers()).thenReturn(userList);
		when(userService.findUserById(TestUtils.newUser4.getId()))
				.thenReturn(generateUser(new User(), TestUtils.newUser4));
		when(userService.saveUser(TestUtils.createUser5)).thenReturn(generateUser(new User(), TestUtils.createUser5));
	}

	//@Test
	public void getUsers() throws Exception {
		mockMvc.perform(get(BASE_URI).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(TestUtils.CONTENT_TYPE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(TestUtils.newUser1.getId())))
				.andExpect(jsonPath("$[0].firstName", is(TestUtils.newUser1.getFirstName())))
				.andExpect(jsonPath("$[0].middleName", is(TestUtils.newUser1.getMiddleName())))
				.andExpect(jsonPath("$[0].lastName", is(TestUtils.newUser1.getLastName())))
				.andExpect(jsonPath("$[0].age", is(TestUtils.newUser1.getAge())))
				.andExpect(jsonPath("$[0].gender", is(TestUtils.newUser1.getGender().toString())))
				.andExpect(jsonPath("$[0].phoneNumber", is(TestUtils.newUser1.getPhoneNumber())))
				.andExpect(jsonPath("$[0].zipcode", is(TestUtils.newUser1.getZipcode())))
				.andExpect(jsonPath("$[1].id", is(TestUtils.newUser2.getId())))
				.andExpect(jsonPath("$[1].firstName", is(TestUtils.newUser2.getFirstName())))
				.andExpect(jsonPath("$[1].middleName", is(TestUtils.newUser2.getMiddleName())))
				.andExpect(jsonPath("$[1].lastName", is(TestUtils.newUser2.getLastName())))
				.andExpect(jsonPath("$[1].age", is(TestUtils.newUser2.getAge())))
				.andExpect(jsonPath("$[1].gender", is(TestUtils.newUser2.getGender().toString())))
				.andExpect(jsonPath("$[1].phoneNumber", is(TestUtils.newUser2.getPhoneNumber())))
				.andExpect(jsonPath("$[1].zipcode", is(TestUtils.newUser2.getZipcode())));

	}

	@Test
	public void getUser() throws Exception {
		mockMvc.perform(get(BASE_URI + "/{id}", TestUtils.newUser4.getId()).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(TestUtils.CONTENT_TYPE))
				.andExpect(jsonPath("$.id", is(TestUtils.newUser4.getId())))
				.andExpect(jsonPath("$.firstName", is(TestUtils.newUser4.getFirstName())))
				.andExpect(jsonPath("$.middleName", is(TestUtils.newUser4.getMiddleName())))
				.andExpect(jsonPath("$.lastName", is(TestUtils.newUser4.getLastName())))
				.andExpect(jsonPath("$.age", is(TestUtils.newUser4.getAge())))
				.andExpect(jsonPath("$.gender", is(TestUtils.newUser4.getGender().toString())))
				.andExpect(jsonPath("$.phoneNumber", is(TestUtils.newUser4.getPhoneNumber())))
				.andExpect(jsonPath("$.zipcode", is(TestUtils.newUser4.getZipcode())));

	}

	@Test
	public void createUser() throws Exception {

		mockMvc.perform(post(BASE_URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.convertObjectToJsonBytes(TestUtils.createUser5)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(TestUtils.CONTENT_TYPE));
	}

	/**
	 * get User's list- add test data
	 */
	private void addUsers(List<User> userList) {
		User user1 = new User();
		User user2 = new User();
		user1.setId(TestUtils.newUser1.getId());
		user1.clone(TestUtils.newUser1);
		user2.setId(TestUtils.newUser2.getId());
		user2.clone(TestUtils.newUser2);
		userList.add(user1);
		userList.add(user2);
	}

	/**
	 * User - test data
	 * 
	 * @return
	 */
	private User generateUser(User copyUser, User user) {
		copyUser.setId(user.getId());
		copyUser.clone(user);
		return copyUser;
	}
}