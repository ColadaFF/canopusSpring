package co.com.ias.demos.canopus.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.google.common.base.Preconditions.checkNotNull;

@Document(collection = "locations")
public class Location {
    @Id
    private ObjectId id;
    private Double latitude;
    private Double longitude;

    public Location(Double latitude, Double longitude, ObjectId id) {
        this.latitude = checkNotNull(latitude, "latitude can't be null");
        this.longitude = checkNotNull(longitude, "longitude can't be null");
        this.id = checkNotNull(id, "id can't be null");
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public ObjectId getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
