-- Create the database
CREATE DATABASE weatherdb;

-- Use the database
USE weatherdb;

-- Create the weather_info table
CREATE TABLE weather_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pincode VARCHAR(10) NOT NULL,
    lat DECIMAL(10, 6) NOT NULL,
    lon DECIMAL(10, 6) NOT NULL,
    weather_data JSON NOT NULL,
    for_date DATE NOT NULL,
    UNIQUE KEY unique_pincode_date (pincode, for_date)  -- Ensures pincode and date are unique together
);
