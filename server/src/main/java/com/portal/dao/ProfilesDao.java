package com.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portal.models.ProfileModel;

public interface ProfilesDao extends JpaRepository<ProfileModel, Integer> {

}
