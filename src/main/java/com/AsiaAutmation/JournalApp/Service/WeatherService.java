package com.AsiaAutmation.JournalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    private static final String API_KEY="5cb41ba4e7739d0f4b9275c05ead2854";
    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public WeatherResponse getResponse(String city){
        String finalApi = API.replace("API_KEY",API_KEY).replace("CITY",city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
