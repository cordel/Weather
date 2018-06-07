package me.suzdalnitsky.weather.domain.interactors;

import me.suzdalnitsky.weather.data.location.LocationIsNotAccurateException;
import me.suzdalnitsky.weather.domain.models.Location;
import me.suzdalnitsky.weather.domain.models.Weather;
import me.suzdalnitsky.weather.repositories.LocationRepository;
import me.suzdalnitsky.weather.repositories.WeatherRepository;

public class MainInteractor {

    private LocationRepository locationRepository;
    private WeatherRepository weatherRepository;

    private static final int LOCATION_ACCURACY_THRESHOLD = 100;

    public MainInteractor(LocationRepository locationRepository, WeatherRepository weatherRepository) {
        this.locationRepository = locationRepository;
        this.weatherRepository = weatherRepository;
    }

    public Weather getLocalWeather() throws Exception {
        Location location = locationRepository.getLocation();
        if (location.getHorizontalAccuracyMeters() > LOCATION_ACCURACY_THRESHOLD) {
            throw new LocationIsNotAccurateException();
        }
        return weatherRepository.fetchWeather(location);
    }

    public Weather getWeather(String cityName) throws Exception {
        return weatherRepository.fetchWeather(cityName);
    }
}
