package com.portal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"areaOfExpertise", "yearsOfExperience"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileModel {

	@Id
	@GeneratedValue
	private int empId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String areaOfExpertise;
	
	private Integer yearsOfExperience;
}
