package com.portal.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.dao.ProfilesDao;
import com.portal.models.ProfileModel;

@Service
public class EmployeeService {

	@Autowired
	private ProfilesDao dao;

	private ClassPathResource resource = new ClassPathResource("data/employee_profiles.json");

	@PostConstruct
	public void addDefaultEmployees() {
		try {
			List<ProfileModel> defaultList = new ObjectMapper().readValue(resource.getFile(),
					new TypeReference<List<ProfileModel>>() {
					});
			dao.saveAll(defaultList);
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
		ProfileModel currentDtls = dao.getById(id);
		BeanUtils.copyProperties(model, currentDtls);
		ProfileModel saveResponse = dao.save(currentDtls);
		updateDefaultFile();
		return saveResponse;
	}

	private void updateDefaultFile() {
		try (FileOutputStream outputStream = new FileOutputStream(resource.getFile())) {
			List<ProfileModel> entireList = dao.findAll();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(entireList);
			outputStream.write(bos.toByteArray());
		} catch (Exception exp) {
			throw new RestClientException("Error occured in updating default data file: " + exp.getMessage());
		}
	}
}
