package me.suzdalnitsky.weather.data.location;

public class LocationNotPermittedException extends IllegalStateException {

    LocationNotPermittedException(Throwable cause) {
        super(cause);
    }
}
