package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Cache.AppCache;
import com.AsiaAutmation.JournalApp.Enums.Keys;
import com.AsiaAutmation.JournalApp.Constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    AppCache appCache;

    public WeatherResponse getResponse(String city){
        WeatherResponse response = redisService.get(city,WeatherResponse.class);
        if(response != null){
            return response;
        }
        else{
            String finalApi = appCache.getAppCache().get(Keys.WEATHER_API.toString()).replace(PlaceHolders.API_KEY,apiKey).replace(PlaceHolders.CITY,city);
            //HttpHeaders httpHeaders = new HttpHeaders();
            //httpHeaders.add("username","admin");
            //httpHeaders.add("password","admin");
            //HttpEntity<String> httpEntity = new HttpEntity<>("Hello there",httpHeaders);
            ResponseEntity<WeatherResponse> response1 = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            redisService.set(city,response1.getBody(),300l);
            //ResponseEntity<WeatherResponse> response2 = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherResponse.class);
            return response1.getBody();
        }
    }
}
