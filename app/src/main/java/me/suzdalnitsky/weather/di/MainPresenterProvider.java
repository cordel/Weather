package me.suzdalnitsky.weather.di;

import com.google.gson.Gson;

import me.suzdalnitsky.weather.App;
import me.suzdalnitsky.weather.BuildConfig;
import me.suzdalnitsky.weather.data.location.LocationProvider;
import me.suzdalnitsky.weather.data.mappers.SystemLocationMapper;
import me.suzdalnitsky.weather.data.mappers.WeatherApiResponseMapper;
import me.suzdalnitsky.weather.data.network.WeatherWebApiClient;
import me.suzdalnitsky.weather.data.network.client.BasicHttpClient;
import me.suzdalnitsky.weather.data.network.parser.GsonParser;
import me.suzdalnitsky.weather.domain.interactors.MainInteractor;
import me.suzdalnitsky.weather.presentation.main.MainPresenter;
import me.suzdalnitsky.weather.repositories.LocationRepository;
import me.suzdalnitsky.weather.repositories.WeatherRepository;

public class MainPresenterProvider {

    public static MainPresenter provide() {
        return new MainPresenter(
                new MainInteractor(
                        new LocationRepository(
                                new LocationProvider(App.getContext()),
                                new SystemLocationMapper()
                        ),
                        new WeatherRepository(
                                new WeatherWebApiClient(
                                        new BasicHttpClient(
                                                new GsonParser(
                                                        new Gson()
                                                )
                                        ),
                                        BuildConfig.API_KEY
                                ),
                                new WeatherApiResponseMapper()
                        )
                )
        );
    }
}
