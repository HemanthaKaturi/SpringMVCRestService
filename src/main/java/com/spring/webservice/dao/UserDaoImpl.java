package com.spring.webservice.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jayway.jsonpath.Criteria;
import com.spring.webservice.exception.ServiceException;
import com.spring.webservice.model.User;

/**
 * Implementation of DAO Layer
 * 
 * @author Hemantha
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	private SessionFactory sessionFactory;

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	@Transactional(readOnly = false)
	public User saveUser(User newUser) throws ServiceException {
		try {
			Object obj = hibernateTemplate.save(newUser);

			return getUserById(obj.toString());
		} catch (HibernateException ex) {
			throw new ServiceException("Exception while creating new User[ " + newUser.toString() + "]", ex);
		}
	}

	@Transactional
	public User getUserById(Serializable id) throws ServiceException {
		try {
			return (User) hibernateTemplate.get(User.class, id);
		} catch (HibernateException ex) {
			throw new ServiceException("Exception while retreiving User with id= " + id, ex);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getUsers(String propertyName) throws ServiceException {
		try {
			org.hibernate.Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
			
			criteria.addOrder(Order.asc(propertyName));
			List<User> listUser = criteria.list();
		//	List<User> listUser = (List<User>) hibernateTemplate.find("from " + User.class.getName());
			return listUser;
		} catch (HibernateException ex) {
			throw new ServiceException("Exception while retreiving list of Users ", ex);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public User updateUser(User user) throws ServiceException {
		try {
			User currentUser = getUserById(user.getId());
			if (currentUser == null) {
				throw new ServiceException("User with id[ " + user.getId() + "] does not exist. ");
			}
			currentUser.setFirstName(user.getFirstName());
			currentUser.setMiddleName(user.getMiddleName());
			currentUser.setLastName(user.getLastName());
			currentUser.setAge(user.getAge());
			currentUser.setGender(user.getGender());
			currentUser.setPhoneNumber(user.getPhoneNumber());
			currentUser.setZipcode(user.getZipcode());
			//currentUser.clone(user);
			hibernateTemplate.update(currentUser);
			return currentUser;
		} catch (HibernateException ex) {
			throw new ServiceException("Exception while updating User with id= " + user.getId(), ex);
		}
	}

	@Override
	@Transactional
	public User getUserById(String id) throws ServiceException {
		try {
			if (StringUtils.isEmpty(id)) {
				throw new ServiceException(" User with id[ " + id + "] does not exist. ");
			}
			return (User) hibernateTemplate.get(User.class, id);
		} catch (HibernateException ex) {
			throw new ServiceException("Exception while retreiving User with id= " + id, ex);
		}
	}

	@Override
	public boolean isUserExist(String id) throws ServiceException {
		return getUserById(id) != null;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteUserById(String id) throws ServiceException {
		try {
			hibernateTemplate.delete(getUserById(id));
			return true;
		} catch (HibernateException ex) {
			throw new ServiceException("Exception while deleting User with id= " + id, ex);
		}
	}

}
