package com.hertzbit.restapplication.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String sports;
	private String country;
	private Integer salary;

	public User(Integer userId, String firstName, String lastName, String sports, String country, Integer salary) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sports = sports;
		this.country = country;
		this.salary = salary;
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

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", sports='" + sports + '\'' +
				", country='" + country + '\'' +
				", salary=" + salary +
				'}';
	}
}
