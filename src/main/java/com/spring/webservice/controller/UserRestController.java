package com.spring.webservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.webservice.exception.ServiceException;
import com.spring.webservice.model.User;
import com.spring.webservice.service.IUserService;
import com.spring.webservice.util.ErrorMessage;

/**
 * Rest Service mapping based on url mapping & requestmethod
 * 
 * @author Hemantha
 */
@RestController
public class UserRestController {

	@Autowired
	IUserService userService;

	/**
	 * List all Users - details
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listUsers(@RequestParam("sortField") String sortField) throws ServiceException {
		final List<User> users = userService.getUsers( sortField);
		if (users == null || users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * Get User Details if exists
	 * 
	 * @param id
	 * @param ucBuilder
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String id, UriComponentsBuilder ucBuilder)
			throws ServiceException {

		User user = userService.findUserById(id);
		if (user == null) {
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			throw new ServiceException("User with id: " + id + " does not Exist");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(id).toUri());
		return new ResponseEntity<User>(user,headers, HttpStatus.OK);
	}

	/**
	 * Creates User Details if not exists
	 * 
	 * @param user
	 * @param bindingResults
	 * @param ucBuilder
	 * @return
	 * @throws ConstraintViolationException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult bindingResults,
			UriComponentsBuilder ucBuilder) throws ConstraintViolationException, ServiceException {

		if (user.getId() != null && user.getId().length() > 0 && userService.isUserExist(user.getId())) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}

		User newUser = userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setLocation(ucBuilder.path("/user").buildAndExpand().toUri());
		return new ResponseEntity<User>(newUser,headers, HttpStatus.CREATED);
	}

	/**
	 * Updates user details if exists
	 * 
	 * @param user
	 * @param ucBuilder
	 * @return
	 * @throws ConstraintViolationException
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user, UriComponentsBuilder ucBuilder)
			throws ConstraintViolationException, ServiceException {
		User currentUser = userService.updateUser(user);
		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setLocation(ucBuilder.path("/user").buildAndExpand().toUri());
		return new ResponseEntity<User>(currentUser,headers, HttpStatus.ACCEPTED);
	}

	/**
	 * Delete user if exists
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		User user = userService.findUserById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(user.getId());
		return new ResponseEntity<User>(HttpStatus.ACCEPTED);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> customError(HttpServletRequest request, Exception exception) {
		ErrorMessage error = new ErrorMessage();
		error.setStatus(HttpStatus.EXPECTATION_FAILED.value());
		error.setMessage(exception.getLocalizedMessage());
		error.setUrl(request.getRequestURL().toString());
		error.setRequestType(request.getMethod());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<ErrorMessage>(error,headers, HttpStatus.EXPECTATION_FAILED);
	}

}
