package com.AsiaAutmation.JournalApp.Scheduler;

import com.AsiaAutmation.JournalApp.Cache.AppCache;
import com.AsiaAutmation.JournalApp.Entity.JournalEntry;
import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Enums.Sentiment;
import com.AsiaAutmation.JournalApp.Repository.UserRepositoryImpl;
import com.AsiaAutmation.JournalApp.Service.EmailService;
import com.AsiaAutmation.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Scheduled(cron = "0 * * ? * *")
    public void reloadCache(){
        appCache.init();
    }

    @Scheduled(cron = "0 0 * ? * *")
    public void fetchUserAndSendSAEmail(){
        List<Users> usersList = userRepository.getUserForSA();
        for(Users user: usersList){
            List<JournalEntry> entries = userService.getUserEntries(user);
            List<String> validEntries = entries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minusDays(7))).map(x-> x.getContent()).toList();
            List<Sentiment> sentiments = entries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minusDays(7))).map(x-> x.getSentiment()).toList();
            String wholeString = String.join("\n",validEntries);
            Map<Sentiment, Integer> sentimentCount = new HashMap<>();
            Sentiment highestSentiment = null;
            int highCount = 0;
            for(Sentiment sentiment: sentiments) {
                if (sentiment != null) {
                    //YouTube Logic
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment,1)+1);
                    //My Logic
//                    sentimentCount.computeIfPresent(sentiment, (k,v)-> ++v);
//                    sentimentCount.computeIfAbsent(sentiment, _ -> 1);
                }
            }
            for(Map.Entry<Sentiment, Integer> e : sentimentCount.entrySet()) {
                if (e.getValue()>highCount){
                    highCount = e.getValue();
                    highestSentiment = e.getKey();
                }
            }
            emailService.sendMail(user.getEmail(),"These are past 7 days entries and your sentiment"
                    ,wholeString+"\nThis was your sentiment throughout the week\n"+highestSentiment);
        }
    }
}
