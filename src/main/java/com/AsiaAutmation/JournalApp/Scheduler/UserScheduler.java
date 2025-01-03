package com.AsiaAutmation.JournalApp.Scheduler;

import com.AsiaAutmation.JournalApp.Cache.AppCache;
import com.AsiaAutmation.JournalApp.Entity.JournalEntry;
import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Repository.UserRepositoryImpl;
import com.AsiaAutmation.JournalApp.Service.EmailService;
import com.AsiaAutmation.JournalApp.Service.SentimentAnalysisService;
import com.AsiaAutmation.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private AppCache appCache;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 * * ? * *")
    public void reloadCache(){
        appCache.init();
    }

    @Scheduled(cron = "0 0 * ? * *")
    public void fetchUserandSendSAEmail(){
        List<Users> usersList = userRepository.getUserForSA();
        for(Users user: usersList){
            List<JournalEntry> entries = userService.getUserEntries(user);
            List<String> validEntries = entries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minusDays(7))).map(x-> x.getContent()).toList();
            String wholeString = String.join(" ",validEntries);
            emailService.sendMail(user.getEmail(),"These are past 7 days entries",sentimentAnalysisService.getSentiment(wholeString));
        }

    }
}
