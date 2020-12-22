package com.hillert.service;

import com.hillert.entity.ProfileEntity;
import com.hillert.model.ProfileModel;
import com.hillert.model.ResponseData;
import com.hillert.repository.ProfileRepository;
import com.sun.crypto.provider.PBEWithMD5AndDESCipher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ResponseData profileAll(){
        ResponseData responseData =new ResponseData();
        Integer statusCode = 204;
        String statusMessage = "Not found";
        List <ProfileEntity> profileEntitys =new ArrayList<>();
        try {
            profileEntitys= (List<ProfileEntity>) profileRepository.findAll();
        }catch (Exception e) {
            statusMessage = e.getMessage();
        }

        responseData.setData(profileEntitys);
        responseData.setStatusCode(statusCode);
        responseData.setStatusMessage(statusMessage);

        return responseData;
    }
    public ResponseData profileById(String id){
        ResponseData responseData =new ResponseData();
        Integer statusCode = 204;
        String statusMessage = "Not found";
        ProfileEntity profileEntity =new ProfileEntity();
        try {
            profileEntity= profileRepository.findOne(id);
            if (profileEntity != null ) {
                 statusCode = 200;
                 statusMessage = "Success";
            }
        }catch (Exception e) {
            statusMessage = e.getMessage();
        }

        responseData.setData(profileEntity);
        responseData.setStatusCode(statusCode);
        responseData.setStatusMessage(statusMessage);

        return responseData;
    }
    public ResponseData profileInsert(ProfileModel profileModel){
        ResponseData responseData =new ResponseData();
        Integer statusCode = 204;
        String statusMessage = "Not found";
        ProfileEntity profileEntity =new ProfileEntity();
        try {
            BeanUtils.copyProperties(profileModel,profileEntity);
            profileRepository.save(profileEntity);
        }catch (Exception e) {
            statusMessage = e.getMessage();
        }

        responseData.setStatusCode(200);
        responseData.setStatusMessage(statusMessage);

        return responseData;
    }
    public ResponseData profileUpdate(ProfileModel profileModel){
        ResponseData responseData =new ResponseData();
        Integer statusCode = 204;
        String statusMessage = "Not found";
        ProfileEntity profileEntity =new ProfileEntity();
        try {
            BeanUtils.copyProperties(profileModel,profileEntity);
            profileRepository.save(profileEntity);
        }catch (Exception e) {
            statusMessage = e.getMessage();
        }

        responseData.setStatusCode(200);
        responseData.setStatusMessage(statusMessage);

        return responseData;
    }
    public ResponseData profileDelete(String id){
        ResponseData responseData =new ResponseData();
        Integer statusCode = 204;
        String statusMessage = "Not found";
        ProfileEntity profileEntity =new ProfileEntity();
        try {
            profileRepository.delete(id);
        }catch (Exception e) {
            statusMessage = e.getMessage();
        }

        responseData.setStatusCode(200);
        responseData.setStatusMessage(statusMessage);

        return responseData;
    }

}
