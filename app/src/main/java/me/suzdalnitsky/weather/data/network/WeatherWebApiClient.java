package me.suzdalnitsky.weather.data.network;

import me.suzdalnitsky.weather.data.network.client.HttpClient;
import me.suzdalnitsky.weather.data.network.models.WeatherApiResponse;
import me.suzdalnitsky.weather.domain.models.Location;
import me.suzdalnitsky.weather.util.Util;

public class WeatherWebApiClient implements WeatherWebApi {

    private final HttpClient httpClient;
    private final String apiKey;

    public WeatherWebApiClient(HttpClient httpClient, String apiKey) {
        this.httpClient = Util.requireNonNull(httpClient);
        this.apiKey = Util.requireNonNull(apiKey);
    }

    @Override
    public WeatherApiResponse getWeather(Location location) throws Exception {
        String requestUrl = prepareRequestUrl(
                location.getLatitude(),
                location.getLongitude(),
                apiKey
        );
        
        return httpClient.performGetRequest(requestUrl, WeatherApiResponse.class);
    }

    @Override
    public WeatherApiResponse getWeather(String cityName) throws Exception {
        String requestUrl = prepareRequestUrl(
                cityName, 
                apiKey
        );

        return httpClient.performGetRequest(requestUrl, WeatherApiResponse.class);
    }

    private String prepareRequestUrl(
            double latitude, double longitude, String apiKey
    ) {
        return "http://api.openweathermap.org/data/2.5/" +
                "weather?lat=" + latitude +
                "&lon=" + longitude +
                "&APIKEY=" + apiKey;
    }

    private String prepareRequestUrl(
            String cityName, String apiKey
    ) {
        return "http://api.openweathermap.org/data/2.5/" +
                "weather?q=" + cityName +
                "&APIKEY=" + apiKey;
    }

}
