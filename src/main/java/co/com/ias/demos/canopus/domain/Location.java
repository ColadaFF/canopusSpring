package co.com.ias.demos.canopus.domain;

import org.bson.types.ObjectId;

import static com.google.common.base.Preconditions.checkNotNull;

public class Location {
    private Double latitude;
    private Double longitude;

    public Location(Double latitude, Double longitude) {
        this.latitude = checkNotNull(latitude, "latitude can't be null");
        this.longitude = checkNotNull(longitude, "longitude can't be null");
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
