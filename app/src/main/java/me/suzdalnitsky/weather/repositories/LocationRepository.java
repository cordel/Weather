package me.suzdalnitsky.weather.repositories;

import java.util.concurrent.ExecutionException;

import me.suzdalnitsky.weather.data.location.LocationProvider;
import me.suzdalnitsky.weather.data.mappers.Mapper;
import me.suzdalnitsky.weather.domain.models.Location;

public class LocationRepository {

    private final LocationProvider locationProvider;
    private final Mapper<android.location.Location, Location> locationMapper;

    public LocationRepository(
            LocationProvider locationProvider, 
            Mapper<android.location.Location, Location> locationMapper
    ) {
        this.locationProvider = locationProvider;
        this.locationMapper = locationMapper;
    }

    public Location getLocation() throws ExecutionException, InterruptedException {
        android.location.Location location = locationProvider.getLocation();
        return locationMapper.map(location);
    }
}
