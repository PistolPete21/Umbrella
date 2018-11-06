package com.nerdery.umbrella.model;

import android.location.Location;

public class WeatherLocation {

    private String name;
    private String latitude;
    private String longitude;
    private String zip;

    public WeatherLocation(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Location getLatitudeLongitude() {
        Location location = new Location("");

        location.setLatitude(Double.parseDouble(getLatitude()));
        location.setLongitude(Double.parseDouble(getLongitude()));

        return location;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
