package com.weatherinfo.pincode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weatherinfo.pincode.entity.WeatherInfo;
import com.weatherinfo.pincode.repository.WeatherRepository;
import com.weatherinfo.pincode.utils.ApiUtils;

import java.util.Date;
import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private ApiUtils apiUtils;

    /**
     * Retrieves weather data for a specific pincode and date.
     * 
     * This method checks if the weather data is already stored in the database. 
     * If the data exists, it returns the cached weather data. Otherwise, it 
     * fetches the latitude and longitude for the provided pincode, retrieves 
     * the weather data from an external API, and saves it to the database.
     *
     * @param pincode The postal code for which weather information is requested.
     * @param forDate The date for which the weather data is requested.
     * @return A JSON string containing the weather data.
     */
    public String getWeatherData(String pincode, Date forDate) {
        // Check if the data is already in the DB
        Optional<WeatherInfo> existingWeather = weatherRepository.findByPincodeAndForDate(pincode, forDate);

        if (existingWeather.isPresent()) {
            return existingWeather.get().getWeatherData();  // Return cached weather data
        }

        // Get Lat/Long for pincode for India
        double[] latLong = apiUtils.getLatLongFromPincode(pincode, "IN");

        // Get weather data from lat/long
        String weatherData = apiUtils.getWeatherFromLatLong(latLong[0], latLong[1]);

        // Save to DB
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setPincode(pincode);
        weatherInfo.setLat(latLong[0]);
        weatherInfo.setLon(latLong[1]);
        weatherInfo.setWeatherData(weatherData);
        weatherInfo.setForDate(forDate);

        weatherRepository.save(weatherInfo);

        return weatherData;
    }
}
