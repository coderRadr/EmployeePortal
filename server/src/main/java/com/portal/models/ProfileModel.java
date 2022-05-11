package com.portal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfileModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int empId;

	private String firstName;

	private String lastName;

	private String email;

	private String areaOfExpertise;

	private Double yearsOfExperience;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaOfExpertise() {
		return areaOfExpertise;
	}

	public void setAreaOfExpertise(String areaOfExpertise) {
		this.areaOfExpertise = areaOfExpertise;
	}

	public Double getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Double yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	@Override
	public String toString() {
		return "ProfileModel [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", areaOfExpertise=" + areaOfExpertise + ", yearsOfExperience=" + yearsOfExperience + "]";
	}
}
