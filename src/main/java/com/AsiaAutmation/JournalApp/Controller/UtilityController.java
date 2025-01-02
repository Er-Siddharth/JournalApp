package com.AsiaAutmation.JournalApp.Controller;

import com.AsiaAutmation.JournalApp.Cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    AppCache appCache;

    @GetMapping("/cache-reload")
    public void cacheClear(){
        appCache.init();
    }
}
