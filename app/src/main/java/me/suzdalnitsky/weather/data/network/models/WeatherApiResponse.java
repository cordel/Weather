package me.suzdalnitsky.weather.data.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherApiResponse {

    @SerializedName("weather")
    private List<RemoteWeatherType> weatherTypes;

    @SerializedName("main")
    private RemoteWeatherBaseStats stats;

    @SerializedName("wind")
    private RemoteWeatherWindSpeed windSpeed;

    @SerializedName("name")
    private String cityName;
    
    public List<RemoteWeatherType> getWeatherTypes() {
        return weatherTypes;
    }

    public RemoteWeatherBaseStats getStats() {
        return stats;
    }

    public RemoteWeatherWindSpeed getWindSpeed() {
        return windSpeed;
    }

    public String getCityName() {
        return cityName;
    }
    
}
