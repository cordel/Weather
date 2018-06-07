package me.suzdalnitsky.weather.data.network.models;

public class RemoteWeatherBaseStats {

    private float temp;
    private int pressure;
    private int humidity;

    public float getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }
}
