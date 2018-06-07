package me.suzdalnitsky.weather.domain.models;

import android.support.annotation.NonNull;

import me.suzdalnitsky.weather.util.Util;

public final class Weather {

    @NonNull
    private final String cityName;
    @NonNull
    private final String type;
    @NonNull
    private final Integer pressure;
    @NonNull
    private final Integer humidity;
    @NonNull
    private final Integer tempCelsius;
    @NonNull
    private final Integer windSpeed;

    public Weather(@NonNull String cityName, @NonNull String type, Integer pressure, Integer humidity, Integer tempCelsius, Integer windSpeed) {
        this.cityName = Util.requireNonNull(cityName);
        this.type = Util.requireNonNull(type);
        this.pressure = Util.requireNonNull(pressure);
        this.humidity = Util.requireNonNull(humidity);
        this.tempCelsius = Util.requireNonNull(tempCelsius);
        this.windSpeed = Util.requireNonNull(windSpeed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (!cityName.equals(weather.cityName)) return false;
        if (!type.equals(weather.type)) return false;
        if (!pressure.equals(weather.pressure)) return false;
        if (!humidity.equals(weather.humidity)) return false;
        if (!tempCelsius.equals(weather.tempCelsius)) return false;
        return windSpeed.equals(weather.windSpeed);
    }

    @Override
    public int hashCode() {
        int result = cityName.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + pressure.hashCode();
        result = 31 * result + humidity.hashCode();
        result = 31 * result + tempCelsius.hashCode();
        result = 31 * result + windSpeed.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Weather{");
        sb.append("cityName='").append(cityName).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", pressure=").append(pressure);
        sb.append(", humidity=").append(humidity);
        sb.append(", tempCelsius=").append(tempCelsius);
        sb.append(", windSpeed=").append(windSpeed);
        sb.append('}');
        return sb.toString();
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    @NonNull
    public String getType() {
        return type;
    }

    @NonNull
    public Integer getPressure() {
        return pressure;
    }

    @NonNull
    public Integer getHumidity() {
        return humidity;
    }

    @NonNull
    public Integer getTempCelsius() {
        return tempCelsius;
    }

    @NonNull
    public Integer getWindSpeed() {
        return windSpeed;
    }
}
