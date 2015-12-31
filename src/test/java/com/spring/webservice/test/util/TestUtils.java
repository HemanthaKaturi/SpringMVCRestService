package com.spring.webservice.test.util;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.webservice.model.User;
import com.spring.webservice.model.User.Gender;

public class TestUtils {

	public static final User newUser1 = new User();
	public static final User newUser2 = new User();
	public static final User newUser4 = new User();
	public static final User createUser5 = new User();
	public static final User updateUser3 = new User();
	public static final String CONTENT_TYPE= "Application/json;charset=UTF-8";

	/**
	 * User's list - test data
	 */
	static {

		newUser1.setId("1000001");
		newUser1.setFirstName("DIMPLE");
		newUser1.setLastName("KA");
		newUser1.setAge(31);
		newUser1.setGender(Gender.F);
		newUser1.setPhoneNumber("8125629101");
		newUser1.setZipcode("36543");
		newUser2.setId("1000002");
		newUser2.setFirstName("DIVYA");
		newUser2.setLastName("PN");
		newUser2.setAge(24);
		newUser2.setGender(Gender.M);
		newUser2.setPhoneNumber("7656544310");
		newUser2.setZipcode("35254");
		updateUser3.setId("1000003");
		updateUser3.setFirstName("HENRY");
		updateUser3.setMiddleName("M");
		updateUser3.setLastName("KRISS");
		updateUser3.setAge(39);
		updateUser3.setGender(Gender.F);
		updateUser3.setPhoneNumber("6764983218");
		updateUser3.setZipcode("87452");
		newUser4.setId("1000004");
		newUser4.setFirstName("SAM");
		newUser4.setMiddleName("J");
		newUser4.setLastName("HA");
		newUser4.setAge(31);
		newUser4.setGender(Gender.M);
		newUser4.setPhoneNumber("8125629101");
		newUser4.setZipcode("36543");
		createUser5.setId("1000005");
		createUser5.setFirstName("FIRSTNAME");
		createUser5.setMiddleName("MIDDLENAME");
		createUser5.setLastName("LASTNAME");
		createUser5.setAge(31);
		createUser5.setGender(Gender.M);
		createUser5.setPhoneNumber("8125629101");
		createUser5.setZipcode("36543");
	
	}

	/**
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static String convertObjectToJsonBytes(User object) throws IOException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(object);
		return json;
	}

}
