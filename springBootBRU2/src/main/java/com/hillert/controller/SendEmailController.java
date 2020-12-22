package com.hillert.controller;

import com.hillert.model.ResponseData;
import com.hillert.service.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class SendEmailController {

    @Autowired
    SendEmail sendEmail;

    @GetMapping(value = "/sendEmail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getProfileAll() throws MessagingException {
        sendEmail.prepareAndSendEmail();
    }
}
