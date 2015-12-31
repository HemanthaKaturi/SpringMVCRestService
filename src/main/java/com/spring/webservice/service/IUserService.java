package com.spring.webservice.service;

import java.util.List;

import com.spring.webservice.exception.ServiceException;
import com.spring.webservice.model.User;

/**
 * Interface for Service layer 
 * Services available for controller class
 * 
 * @author Hemantha
 *
 */
public interface IUserService {
	
	public User saveUser(User user) throws ServiceException;
	
	public List<User> getUsers(String propertyName) throws ServiceException; 

	public User updateUser(User user) throws ServiceException;
	
	public User findUserById(String id) throws ServiceException;
	
	public boolean isUserExist(String id) throws ServiceException;
	
	public boolean deleteUserById(String id) throws ServiceException;	
	
}

