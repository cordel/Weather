package me.suzdalnitsky.weather.data.location;

public class LocationNotAvailableException extends IllegalStateException {

    LocationNotAvailableException() { 
        super("Couldn't retrieve location");
    }
}
