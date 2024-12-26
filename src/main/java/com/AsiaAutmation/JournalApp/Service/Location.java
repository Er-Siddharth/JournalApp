package com.AsiaAutmation.JournalApp.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private String timezoneId;
    private String localtime;
    private Integer localtimeEpoch;
    private String utcOffset;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getTimezoneId() {
        return timezoneId;
    }
    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }
    public String getLocaltime() {
        return localtime;
    }
    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }
    public Integer getLocaltimeEpoch() {
        return localtimeEpoch;
    }
    public void setLocaltimeEpoch(Integer localtimeEpoch) {
        this.localtimeEpoch = localtimeEpoch;
    }
    public String getUtcOffset() {
        return utcOffset;
    }
    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }
}
