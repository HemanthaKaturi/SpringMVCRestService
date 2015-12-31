package com.spring.webservice.dao;

import java.util.List;

import com.spring.webservice.exception.ServiceException;
import com.spring.webservice.model.User;

/**
 * Interface to configure defaults DAO methods
 * 
 * @author Hemantha
 */
public interface IUserDao {

	public User saveUser(User newUser) throws ServiceException;

	public List<User> getUsers(String propertyName) throws ServiceException;

	public User updateUser(User updateUser) throws ServiceException;

	public User getUserById(String id) throws ServiceException;

	public boolean isUserExist(String id) throws ServiceException;

	public boolean deleteUserById(String id) throws ServiceException;

}
