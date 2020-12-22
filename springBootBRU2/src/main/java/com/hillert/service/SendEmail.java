package com.hillert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.*;

@Service
public class SendEmail extends HttpServlet {

    public void SandDingEmail(){
        // Recipient's email ID needs to be mentioned.
        String to = "kittiphong95@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "kittiphong.aumphimai@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
//        Properties properties = System.getProperties();

        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);

        Properties props=new Properties();
        props.put("mail.smtp.host","localhost");   //  'localhost' for testing
        Session   session  =  Session.getDefaultInstance(props,null);

        // Get the default Session object.
//        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private static TemplateEngine templateEngine;
    private static Context context;

    private JavaMailSender emailSender;
    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void prepareAndSendEmail() throws MessagingException {
        String htmlTemplate = "templates/emailTemplate";
        String mailTo = "kittiphong95@gmail.com";
        initializeTemplateEngine();

        context.setVariable("sender", "Thymeleaf Email");
        context.setVariable("mailTo", mailTo);

        String htmlBody = templateEngine.process(htmlTemplate, context);
        sendEmail(mailTo, "Thymeleaf Email Demo", htmlBody);
    }

    private void sendEmail(String mailTo, String subject, String mailBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(mailTo);
        helper.setSubject(subject);
        helper.setText(mailBody, true);
        emailSender.send(message);
//        sysyout("Email sent to " + mailTo);
    }

    private static void initializeTemplateEngine() {

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode("HTML5");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        context = new Context(Locale.US);
    }
}
