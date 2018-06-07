package me.suzdalnitsky.weather.data.network;

import me.suzdalnitsky.weather.data.network.models.WeatherApiResponse;
import me.suzdalnitsky.weather.domain.models.Location;

public interface WeatherWebApi {

    WeatherApiResponse getWeather(Location location) throws Exception;

    WeatherApiResponse getWeather(String cityName) throws Exception;
}
