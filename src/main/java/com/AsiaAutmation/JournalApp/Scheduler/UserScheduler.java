package com.AsiaAutmation.JournalApp.Scheduler;

import com.AsiaAutmation.JournalApp.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserScheduler {

    @Autowired
    private AppCache appCache;
    @Scheduled(cron = "0 0/10 0 ? * * *")
    public void reloadCache(){
        appCache.init();
    }

}
