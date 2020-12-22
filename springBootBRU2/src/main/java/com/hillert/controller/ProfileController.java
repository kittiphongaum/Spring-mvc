package com.hillert.controller;

import com.hillert.entity.ProfileEntity;
import com.hillert.model.ProfileModel;
import com.hillert.model.ResponseData;
import com.hillert.repository.ProfileRepository;
import com.hillert.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profile/")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getProfileAll(){
        return profileService.profileAll();
    }

    @GetMapping(value = "get/{emp_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getProfile(@PathVariable String emp_id){
        return profileService.profileById(emp_id);
    }

    @PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getInsert(@RequestBody ProfileModel profileModel){
        return profileService.profileInsert(profileModel);
    }
    @PostMapping(value = "update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getUpdate(@RequestBody ProfileModel profileModel){
        return profileService.profileUpdate(profileModel);
    }
    @GetMapping(value = "delete/{empId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseData getDelete(@PathVariable String empId){
        return  profileService.profileDelete(empId);
    }
}
