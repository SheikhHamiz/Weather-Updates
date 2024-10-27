package com.example.weather_app;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class WeatherScheduler {
    private final WeatherService weatherService;
    private final JavaMailSender mailSender;
    private final UserRepo userRepo;

    public WeatherScheduler(WeatherService weatherService, JavaMailSender mailSender, UserRepo userRepo) {
        this.weatherService = weatherService;
        this.mailSender = mailSender;
        this.userRepo = userRepo;
    }
    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES) //every five minutes
    public void updateWeather() {
        for(MetroCitiesOfIndia m : MetroCitiesOfIndia.values())
            weatherService.fetchWeather(m.name());
    }
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void sendEmail() {
        List<String> states = new ArrayList<>();
        for(Weather w: weatherService.getAllWeatherStates()) {
            if((w.getTemperature() - 273) >= 35) {
                states.add(w.getCity());
            }
        }
        if(states.isEmpty()) {
            return;
        }
        List<User> users = userRepo.findAll();
        for(User user: users) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("alert if temperature exceeds 35 degrees Celsius");

            message.setText(String.format("In these states %s temperture exceeds 35 degrees", states));

            mailSender.send(message);
        }

    }
 }
