package com.AsiaAutmation.JournalApp.Scheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    UserScheduler userScheduler;
    @Test
    void fetchUserAndSendSAEmailTest(){
        Assertions.assertDoesNotThrow(()-> userScheduler.fetchUserAndSendSAEmail());
    }
}
