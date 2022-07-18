package com.hertzbit.restapplication.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private Integer salary;
	private Address address;
	private String[] contactNumbers;

	public User() {};

	public User(Integer userId, String firstName, String lastName, Integer salary, Address address, String[] contactNumbers) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.address = address;
		this.contactNumbers = contactNumbers;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String[] getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(String[] contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", salary=" + salary +
				", address=" + address +
				", contactNumbers=" + Arrays.toString(contactNumbers) +
				'}';
	}
}
