package me.suzdalnitsky.weather.data.mappers;

import android.text.TextUtils;

import java.util.List;

import me.suzdalnitsky.weather.data.network.models.RemoteWeatherType;
import me.suzdalnitsky.weather.data.network.models.WeatherApiResponse;
import me.suzdalnitsky.weather.domain.models.Weather;
import me.suzdalnitsky.weather.util.Util;

public class WeatherApiResponseMapper implements Mapper<WeatherApiResponse, Weather> {

    private static final float ZERO_KELVIN_IN_CELSIUS = -273.15f;
    
    @Override
    public Weather map(WeatherApiResponse weatherApiResponse) {
        List<String> weatherTypeNames = Util.map(
                weatherApiResponse.getWeatherTypes(), RemoteWeatherType::getName
        ); 
        String weatherType = TextUtils.join(", ", weatherTypeNames);

        float temp = kelvinToCelsius(weatherApiResponse.getStats().getTemp()); 

        return new Weather(
                weatherApiResponse.getCityName(),
                weatherType,
                weatherApiResponse.getStats().getPressure(),
                weatherApiResponse.getStats().getHumidity(),
                (int) temp,
                (int) weatherApiResponse.getWindSpeed().getSpeed()
        );
    }

    private float kelvinToCelsius(float kelvinValue) {
        return (kelvinValue + ZERO_KELVIN_IN_CELSIUS);
    }

}
