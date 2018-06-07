package me.suzdalnitsky.weather.data.mappers;

import me.suzdalnitsky.weather.domain.models.Location;

public class SystemLocationMapper implements Mapper<android.location.Location,Location> {

    @Override
    public Location map(android.location.Location location) {
        return new Location(
                location.getLatitude(),
                location.getLongitude(),
                location.getAccuracy()
        );
    }
}
