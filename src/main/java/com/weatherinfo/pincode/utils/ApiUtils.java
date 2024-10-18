package com.weatherinfo.pincode.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class ApiUtils {

    private final String OPENWEATHER_API_KEY = "your-api-key";
    private final String GEO_API_URL = "https://api.openweathermap.org/geo/1.0/zip?zip={pincode},{countryCode}&appid=" + OPENWEATHER_API_KEY;
    private final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid=" + OPENWEATHER_API_KEY;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Retrieves latitude and longitude based on the provided pincode using OpenWeather Geocoding API.
     *
     * @param pincode The pincode to get the latitude and longitude for.
     * @param countryCode The country code (e.g., "IN" for India).
     * @return An array containing latitude and longitude, or dummy values if an error occurs.
     */
    public double[] getLatLongFromPincode(String pincode, String countryCode) {
        String response = restTemplate.getForObject(GEO_API_URL, String.class, pincode, countryCode);

        try {
            // Parse the response using Jackson ObjectMapper
            JsonNode root = objectMapper.readTree(response);
            double lat = root.path("lat").asDouble();
            double lon = root.path("lon").asDouble();

            // Return lat and long as an array
            return new double[]{lat, lon};
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            // Fallback in case of any error
            return new double[]{0.0, 0.0};  // Dummy values in case of error
        }
    }

    // Method to get weather data based on lat/long using OpenWeather API
    public String getWeatherFromLatLong(double lat, double lon) {
        String weatherResponse = restTemplate.getForObject(WEATHER_API_URL, String.class, lat, lon);
        return weatherResponse;  // Returns raw JSON data
    }
}