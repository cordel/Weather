package me.suzdalnitsky.weather.repositories;

import me.suzdalnitsky.weather.data.mappers.Mapper;
import me.suzdalnitsky.weather.data.network.WeatherWebApi;
import me.suzdalnitsky.weather.data.network.models.WeatherApiResponse;
import me.suzdalnitsky.weather.domain.models.Location;
import me.suzdalnitsky.weather.domain.models.Weather;

public class WeatherRepository {

    private final WeatherWebApi weatherWebApi;
    private final Mapper<WeatherApiResponse, Weather> apiResponseMapper;

    public WeatherRepository(WeatherWebApi weatherWebApi, Mapper<WeatherApiResponse, Weather> apiResponseMapper) {
        this.weatherWebApi = weatherWebApi;
        this.apiResponseMapper = apiResponseMapper;
    }

    public Weather fetchWeather(Location location) throws Exception {
        WeatherApiResponse apiResponse = weatherWebApi.getWeather(location);
        return apiResponseMapper.map(apiResponse);
    }

    public Weather fetchWeather(String cityName) throws Exception {
        WeatherApiResponse apiResponse = weatherWebApi.getWeather(cityName);
        return apiResponseMapper.map(apiResponse);
    }
    
}
