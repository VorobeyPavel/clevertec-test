package ru.pvorobey.checkrunnerconsole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Scanner;

@Component
public class MailSender implements MailService{

    @Autowired
    private JavaMailSender mailSender;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendEmail(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        if (!emailTo.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")){
            return;
        }

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);         //адрессат
        mailMessage.setSubject(subject);    //тема
        mailMessage.setText(message);       //содержание

        mailSender.send(mailMessage);
    }

    @Override
    public String setToMail() {
        System.out.println("Please enter an email address if you would like to email a check. Otherwise press Enter");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        if (!Objects.equals(email, "")){
            return email;
        }
        return "";
    }
}
