package com.spring.webservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * User Entity class
 * 
 * @author Hemantha
 */
@Entity
@Table(name = "USER")

public class User implements Serializable {
	private static final long serialVersionUID = -1786993154676L;

	public static enum Gender {
		M, F
	};

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "UUID")
	private String id;

	@Column(name = "FIRST_NAME")
	@NotNull(message = "First Name cannot be Blank")
	@Size(min = 2, max = 32, message = "The First Name provided '${firstName}' must be of size between {min} -{max}")
	@Pattern(regexp = "[A-Za-z]*", message = "First Name should contain alphabets only")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	@Pattern(regexp = "[A-Za-z]*", message = "Middle Name should contain alphabets only")
	private String middleName;

	@Column(name = "LAST_NAME")
	@NotNull(message = "Last Name cannot be Blank")
	@Size(min = 2, max = 25, message = "The Last Name provided '${lastName}' must be of size between {min} -{max}")
	@Pattern(regexp = "[A-Za-z]*", message = "Last Name should contain alphabets only")
	private String lastName;

	@Column(name = "AGE")
	@Min(value = 1, message = "Age Min value is 1")
	@NotNull(message = "Age is mandatory")
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER")
	@NotNull(message = "Gender is mandatory")
	private Gender gender;

	@Column(name = "PHONE_NUMBER")
	@Pattern(regexp = "^[1-9]\\d*", message = "PhoneNumber is should contain only digits & not start with zero ")
	@Size(min = 10, max = 10, message = "The phoneNumber provided '${phoneNumber}' must be of size {min}")
	@NotNull(message = "Provide PhoneNumber for User")
	private String phoneNumber;

	@Column(name = "ZIPCODE")
	@Size(min = 5, max = 5, message = "The zicode provided '${zipcode}' must be of size {min}")
	@Pattern(regexp = "[0-9]*")
	private String zipcode;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public User clone(User user) {
		// this.id = user.id;
		this.firstName = user.getFirstName();
		this.middleName = user.getMiddleName();
		this.lastName = user.getLastName();
		this.age = user.getAge();
		this.gender = user.getGender();
		this.phoneNumber = user.getPhoneNumber();
		this.zipcode = user.getZipcode();
		return this;
	}

}
