package ru.pvorobey.checkrunnerconsole.service;


public interface MailService {

    void sendEmail(String emailTo, String subject, String message);

    String setToMail();
}
