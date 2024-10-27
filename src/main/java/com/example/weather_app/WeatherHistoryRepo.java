package com.example.weather_app;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherHistoryRepo extends JpaRepository<WeatherHistory, Long> {
    List<WeatherHistory> findByMetroCitiesOfIndia(String city);
}
