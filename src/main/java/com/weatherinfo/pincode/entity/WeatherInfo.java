package com.weatherinfo.pincode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Entity class representing weather information for a specific pincode and date.
 */
@Entity
@Table(name = "weather_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {
    
    /**
     * Unique identifier for the weather information entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The pincode for which the weather information is recorded.
     */
    private String pincode;

    /**
     * Latitude coordinate for the location associated with the pincode.
     */
    private double lat;

    /**
     * Longitude coordinate for the location associated with the pincode.
     */
    private double lon;

    /**
     * Weather data in JSON format for the given date.
     */
    @Column(columnDefinition = "JSON")
    private String weatherData;

    /**
     * The date for which the weather information is relevant.
     */
    @Temporal(TemporalType.DATE)
    private Date forDate;
}
