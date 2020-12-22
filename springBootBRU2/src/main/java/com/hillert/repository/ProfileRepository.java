package com.hillert.repository;

import com.hillert.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;


public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {
}
