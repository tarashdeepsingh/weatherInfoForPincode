package com.weatherinfo.pincode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherinfo.pincode.entity.WeatherInfo;

import java.util.Optional;
import java.util.Date;

public interface WeatherRepository extends JpaRepository<WeatherInfo, Long> {
    Optional<WeatherInfo> findByPincodeAndForDate(String pincode, Date forDate);
}