package com.weatherinfo.pincode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.weatherinfo.pincode.service.WeatherService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * Retrieves weather information for a given pincode and date.
     * 
     * @param request A map containing "pincode" and "for_date".
     * @return ResponseEntity with weather data or error message.
     * @throws ParseException if the date format is invalid.
     */
    @PostMapping("/getWeather")
    public String getWeather(@RequestBody Map<String, String> request) throws ParseException {
        String pincode = request.get("pincode");
        Date forDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.get("for_date"));

        return weatherService.getWeatherData(pincode, forDate);
    }
}
