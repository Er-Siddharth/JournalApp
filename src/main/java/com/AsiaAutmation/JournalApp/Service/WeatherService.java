package com.AsiaAutmation.JournalApp.Service;

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
    private RestTemplate restTemplate;
    @Value("${weather.api.key}")
    private String API_KEY;
    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public WeatherResponse getResponse(String city){
        String finalApi = API.replace("API_KEY",API_KEY).replace("CITY",city);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("username","admin");
        httpHeaders.add("password","admin");
        HttpEntity<String> httpEntity = new HttpEntity<>("Hello there",httpHeaders);
        ResponseEntity<WeatherResponse> response1 = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        ResponseEntity<WeatherResponse> response2 = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherResponse.class);
        return response1.getBody();
    }
}
