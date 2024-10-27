package com.example.weather_app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private double temperature;
    private double feelsLike;
    private String condition;
    private LocalDateTime timestamp;
    @JsonIgnore
    @ManyToOne
    private WeatherHistory weatherHistory;

    public Weather(long id, String city, double temperature, double feelsLike, String condition, LocalDateTime timestamp, WeatherHistory weatherHistory) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.condition = condition;
        this.timestamp = timestamp;
        this.weatherHistory = weatherHistory;
    }

    public Weather() {
    }

    public WeatherHistory getWeatherHistory() {
        return weatherHistory;
    }

    public void setWeatherHistory(WeatherHistory weatherHistory) {
        this.weatherHistory = weatherHistory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
