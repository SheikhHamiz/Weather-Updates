package com.example.weather_app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class WeatherHistory {
    @Id
    private long id;
    @OneToMany(mappedBy = "weatherHistory")
    private List<Weather> weathers;
    @Column(unique = true)
    private String metroCitiesOfIndia;

    public WeatherHistory(long id, List<Weather> weathers, String metroCitiesOfIndia) {
        this.id = id;
        this.weathers = weathers;
        this.metroCitiesOfIndia = metroCitiesOfIndia;
    }

    public WeatherHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public String getMetroCitiesOfIndia() {
        return metroCitiesOfIndia;
    }

    public void setMetroCitiesOfIndia(String metroCitiesOfIndia) {
        this.metroCitiesOfIndia = metroCitiesOfIndia;
    }
}
