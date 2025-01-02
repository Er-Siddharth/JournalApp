package com.AsiaAutmation.JournalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendMailTest(){
        emailService.sendMail("saurabhshinde6652@gmail.com","Test Mail","This is java mailsender test mail");
    }
}
