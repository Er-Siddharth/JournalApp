package com.AsiaAutmation.JournalApp.Service;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Current {
    private String observationTime;
    private Integer temperature;
    private Integer weatherCode;
    private List<String> weatherIcons = new ArrayList<String>();
    private List<String> weatherDescriptions = new ArrayList<String>();
    private Integer windSpeed;
    private Integer windDegree;
    private String windDir;
    private Integer pressure;
    private Integer precip;
    private Integer humidity;
    private Integer cloudcover;
    private Integer feelslike;
    private Integer uvIndex;
    private Integer visibility;
    private String isDay;

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public void setWeatherIcons(List<String> weatherIcons) {
        this.weatherIcons = weatherIcons;
    }

    public void setWeatherDescriptions(List<String> weatherDescriptions) {
        this.weatherDescriptions = weatherDescriptions;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public void setPrecip(Integer precip) {
        this.precip = precip;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public void setCloudcover(Integer cloudcover) {
        this.cloudcover = cloudcover;
    }

    public void setFeelslike(Integer feelslike) {
        this.feelslike = feelslike;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public void setIsDay(String isDay) {
        this.isDay = isDay;
    }
}
