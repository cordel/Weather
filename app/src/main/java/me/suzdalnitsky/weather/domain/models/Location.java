package me.suzdalnitsky.weather.domain.models;

public final class Location {

    private final double latitude;
    private final double longitude;
    private final float horizontalAccuracyMeters;

    public Location(double latitude, double longitude, float horizontalAccuracyMeters) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.horizontalAccuracyMeters = horizontalAccuracyMeters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Double.compare(location.latitude, latitude) != 0) return false;
        if (Double.compare(location.longitude, longitude) != 0) return false;
        return Float.compare(location.horizontalAccuracyMeters, horizontalAccuracyMeters) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (horizontalAccuracyMeters != +0.0f ? Float.floatToIntBits(horizontalAccuracyMeters) : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", horizontalAccuracyMeters=").append(horizontalAccuracyMeters);
        sb.append('}');
        return sb.toString();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public float getHorizontalAccuracyMeters() {
        return horizontalAccuracyMeters;
    }
}
