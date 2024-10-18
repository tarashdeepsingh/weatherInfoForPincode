# Weather Information API

## Overview
This project is a Spring Boot application that provides weather information based on user-provided pincode and date for India (user can change country as well). It allows users to retrieve weather data efficiently while storing it in a MySQL database, thus avoiding unnecessary API calls.


## Features
- Retrieve weather information for a specific date and pincode.
- Store weather data in a designated MySQL database.
- Optimize API calls by checking the database for existing data before making external API requests.
- Support for both latitude/longitude retrieval and weather information retrieval.

## Technologies Used
- **Java 21**
- **Spring Boot** (for RESTful API development)
- **Spring Data JPA** (for database interaction)
- **MySQL** (for RDBMS)

### Prerequisites
- Java Development Kit (JDK) 21 or later
- MySQL Server
- VS Code or any Java IDE

### Steps to Run the Project
1. **Clone the Repository**:
    ```bash
    git clone [<repository-url>](https://github.com/tarashdeepsingh/weatherInfoForPincode.git)
    cd weatherInfoForPincode
    ```

2. **Set Up MySQL Database**:
    - Create a database named `weatherdb` and execute the provided SQL script **(data.sql)** to set up the necessary tables.

### API Endpoints
1. **Get Weather Information**
   - **Method**: `POST`
   - **Endpoint**: `/api/weather/getWeather`
   - **Request Body Example**:
   ```json
   {
       "pincode": "411014",
       "for_date": "2020-10-15"
   } 
- **Response Body Example**:
   ```json
   {
        "coord": {
            "lon": 73.9158,
            "lat": 18.5685
        },
        "weather": [
            {
            "id": 500,
            "main": "Rain",
            "description": "light rain",
            "icon": "10n"
            }
        ],
        "base": "stations",
        "main": {
            "temp": 299.25,
            "feels_like": 299.25,
            "temp_min": 299.25,
            "temp_max": 299.25,
            "pressure": 1011,
            "humidity": 78,
            "sea_level": 1011,
            "grnd_level": 947
        },
        "visibility": 10000,
        "wind": {
            "speed": 2.02,
            "deg": 120,
            "gust": 2.52
        },
        "rain": {
            "1h": 0.18
        },
        "clouds": {
            "all": 100
        },
        "dt": 1729263244,
        "sys": {
            "country": "IN",
            "sunrise": 1729213134,
            "sunset": 1729255182
        },
        "timezone": 19800,
        "id": 7357900,
        "name": "Viman Nagar",
        "cod": 200
    }
    ```

### External APIs Used
- **OpenWeather Geocoding API:** Used to retrieve latitude and longitude based on the pincode.

`https://api.openweathermap.org/geo/1.0/zip?zip={pincode},{countryCode}&appid={API_KEY}`

- **OpenWeather Current Weather Data API:** Used to fetch weather information based on latitude and longitude.

`https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API_KEY}`

### Steps to Generate an API Key for OpenWeather
- Create an OpenWeather account: https://openweathermap.org/current
- Login to your account and navigate to API keys.
- Copy your API key

### Update you mysql configurations in application.properties
`
spring.datasource.username=your mysql username
spring.datasource.password=your mysql password
`

## Project Directory Structure
``` bash
your-project-root/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── weather/
│   │   │           └── pincode/
│   │   │               ├── controller/
│   │   │               │   └── WeatherController.java
│   │   │               ├── service/
│   │   │               │   └── WeatherService.java
│   │   │               ├── repository/
│   │   │               │   └── WeatherRepository.java
│   │   │               ├── entity/
│   │   │               │   └── WeatherInfo.java
│   │   │               ├── utils/
│   │   │               │   └── ApiUtils.java
│   │   │               └── PincodeApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
│       └── java/
│           └── com/
│               └── weather/
│                   └── pincode/
│                       └── PincodeApplicationTests.java
└── pom.xml
```
