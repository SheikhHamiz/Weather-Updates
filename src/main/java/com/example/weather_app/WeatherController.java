package com.example.weather_app;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {
    private final WeatherService weatherService;
    private final UserService userService;
    private final WeatherHistoryRepo weatherHistoryRepo;

    public WeatherController(WeatherService weatherService, UserService userService, WeatherHistoryRepo weatherHistoryRepo) {
        this.weatherService = weatherService;
        this.userService = userService;
        this.weatherHistoryRepo = weatherHistoryRepo;
    }

    // data of all metropolitan cities in India
    @PostMapping("weather")
    public List<Weather> addWeatherData() {
        List<Weather> weatherList = new ArrayList<>();
        for(MetroCitiesOfIndia m : MetroCitiesOfIndia.values())
            weatherList.add(weatherService.fetchWeather(m.name()));
        return weatherList;
    }
    @PostMapping("user")
    public User addUser(User user) {
        return userService.addUser(user);
    }
    @GetMapping("history")
    public List<Weather> getHistoryOfCity(String city) {
        WeatherHistory weatherHistory = weatherHistoryRepo.findByMetroCitiesOfIndia(city).get(0);
        return weatherHistory.getWeathers();
    }
}
