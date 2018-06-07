package me.suzdalnitsky.weather.data.network.models;

import com.google.gson.annotations.SerializedName;

public class RemoteWeatherType {

    @SerializedName("main")
    private String name;

    public String getName() {
        return name;
    }
}
