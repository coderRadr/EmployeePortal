package com.portal.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.dao.ProfilesDao;
import com.portal.models.ProfileModel;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmployeeService {

	@Autowired
	private ProfilesDao dao;

	private ClassPathResource resource = new ClassPathResource("data/employee_profiles.json");

	@PostConstruct
	public void addDefaultEmployees() {
		try {
			log.info("initializing data in H2 DB");
			List<ProfileModel> defaultList = new ObjectMapper().readValue(resource.getFile(),
					new TypeReference<List<ProfileModel>>() {
					});
			dao.saveAll(defaultList);
			log.info("initializing data in H2 DB complete...");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ProfileModel> getAllEmployeeDtls() {
		return dao.findAll();
	}

	public ProfileModel getEmployeeDtlsById(int id) {
		return dao.getById(id);
	}

	public ProfileModel updateEmployeeDtlsById(Integer id, ProfileModel model) {
		ProfileModel currentDtls = dao.findById(id).orElse(new ProfileModel());
		currentDtls.setAreaOfExpertise(model.getAreaOfExpertise());
		currentDtls.setEmail(model.getEmail());
		currentDtls.setFirstName(model.getFirstName());
		currentDtls.setLastName(model.getLastName());
		currentDtls.setYearsOfExperience(model.getYearsOfExperience());
		ProfileModel saveResponse = dao.save(currentDtls);
		return saveResponse;
	}

	public void deleteEmployee(Integer id) {
		dao.deleteById(id);
	}

	public ProfileModel addEmployee(ProfileModel model) {
		ProfileModel saveResponse = dao.save(model);
		return saveResponse;
	}

}
