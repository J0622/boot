package com.docmall.demo.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.sun.mail.util.MailSSLSocketFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log4j가 보안에 취약하여, 조금 더 안정성이 있는 해당 어노테이션 사용

//스프링이 작동할때 필요한 설정을 관리하는 목적
// servlet-context.xml , root-context.xml 설정파일을 대신하는 클래스들 중의 
@Configuration
@PropertySource("classpath:mail/email.properties") // 해당 어노테이션 사용시 특정 properties에서 찾아오게됨
public class EmailConfig {

	public EmailConfig() throws Exception{
		log.info("EmailConfig.java constructor called");
	}
	
	@Value("${spring.mail.transport.protocol}")
	private String protocol;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean auth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean starttls;
	
	@Value("${spring.mail.debug}")
	private boolean debug;
	
	@Value("${spring.mail.host}")
    private String host;
	
	@Value("${spring.mail.port}")
    private int port;
	
	@Value("${spring.mail.username}")
    private String username;
	
	@Value("${spring.mail.password}")
    private String password;
	
	@Value("${spring.mail.default.encoding}")
    private String encoding;
	
	@Bean // 메소드의 리턴 값을 Bean으로 생성해주는 어노테이션 
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttls);
        properties.put("mail.smtp.debug", debug);
        
        
        //추가
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        //추가 시작
        properties.put("mail.smtp.socketFactory.port", "25");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "true");

        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.socketFactory", sf);
        //추가 끝
        
        
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setPort(port);
        mailSender.setJavaMailProperties(properties);
        mailSender.setDefaultEncoding(encoding);

        return mailSender;
    }
	
	
}
