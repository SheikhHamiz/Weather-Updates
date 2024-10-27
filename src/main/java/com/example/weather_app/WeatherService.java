package com.example.weather_app;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {
    private final String API_KEY = "add your key";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private  final WeatherRepo weatherRepo;
    private final WeatherHistoryRepo weatherHistoryRepo;

    public WeatherService(WeatherRepo weatherRepo, WeatherHistoryRepo weatherHistoryRepo) {
        this.weatherRepo = weatherRepo;
        this.weatherHistoryRepo = weatherHistoryRepo;
    }
    public Weather fetchWeather(String city
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?q=%s&appid=%s", BASE_URL, city, API_KEY);
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setTemperature(response.getMain().getTemp());
        weather.setFeelsLike(response.getMain().getTemp());
        weather.setCondition(response.getWeather()[0].getMain());
        weather.setTimestamp(LocalDateTime.now());

        Weather newWeather = weatherRepo.save(weather);
        WeatherHistory weatherHistory  = weatherHistoryRepo.findByMetroCitiesOfIndia(city).get(0);
        weatherHistory.getWeathers().add(newWeather);
        weatherHistoryRepo.save(weatherHistory);
        return newWeather;
    }
    public List<Weather> getAllWeatherStates() {
        return weatherRepo.findAll();
    }
}
