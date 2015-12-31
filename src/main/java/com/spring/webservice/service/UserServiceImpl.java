package com.spring.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.webservice.dao.IUserDao;
import com.spring.webservice.exception.ServiceException;
import com.spring.webservice.model.User;

/**
 * Service layer implementation
 * 
 * @author Hemantha
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	public User saveUser(User user) throws ServiceException {
		return userDao.saveUser(user);
	}

	@Override
	public List<User> getUsers(String propertyName) throws ServiceException {
		return userDao.getUsers(propertyName);
	}

	@Override
	public User findUserById(String id) throws ServiceException {
		return userDao.getUserById(id);
	}

	@Override
	public User updateUser(User updateUser) throws ServiceException {
		return userDao.updateUser(updateUser);
	}

	@Override
	public boolean deleteUserById(String id) throws ServiceException {
		return userDao.deleteUserById(id);
	}

	@Override
	public boolean isUserExist(String id) throws ServiceException {
		return userDao.isUserExist(id);
	}

}
