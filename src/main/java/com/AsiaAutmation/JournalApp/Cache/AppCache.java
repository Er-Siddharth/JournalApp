package com.AsiaAutmation.JournalApp.Cache;

import com.AsiaAutmation.JournalApp.Entity.ConfigJournalApp;
import com.AsiaAutmation.JournalApp.Repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;
    @Getter
    private Map<String, String> appCache;
    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalApp> allEntry = configJournalAppRepository.findAll();
        for(ConfigJournalApp entry : allEntry){
            appCache.put(entry.getKey(), entry.getValue());
        }
        System.out.println("Cache-Reloaded "+ LocalDateTime.now());
    }
}
