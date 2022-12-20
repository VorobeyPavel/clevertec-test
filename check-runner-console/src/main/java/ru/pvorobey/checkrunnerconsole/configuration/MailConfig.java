package ru.pvorobey.checkrunnerconsole.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.transport.protocol}")
    private String protocol;

    @Value("${spring.mail.smtp.ssl.protocols}")
    private String sslProtocol;

    @Value("${spring.mail.smtp.ssl.trust}")
    private String trust;

    @Value("${spring.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.smtp.starttls.enable}")
    private String enable;

    @Value("${mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.smtp.ssl.protocols", sslProtocol);
        properties.setProperty("mail.smtp.ssl.trust", trust);
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.starttls.enable", enable);
        properties.setProperty("mail.debug", debug);

        return mailSender;
    }
}
