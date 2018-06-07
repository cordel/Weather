package me.suzdalnitsky.weather.data.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Tasks;

import java.util.concurrent.ExecutionException;


public class LocationProvider {

    private FusedLocationProviderClient locationProviderClient;

    public LocationProvider(Context context) {
        this.locationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() throws InterruptedException, ExecutionException {
        android.location.Location location;

        try {
            location = Tasks.await(locationProviderClient.getLastLocation());
        } catch (ExecutionException e) {
            if (!(e.getCause() instanceof SecurityException)) throw e;
            throw new LocationNotPermittedException(e);
        }

        if (location == null) throw new LocationNotAvailableException();
        return location;
    }
}
