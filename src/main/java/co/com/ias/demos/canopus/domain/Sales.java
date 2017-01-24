package co.com.ias.demos.canopus.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sales")
public class Sales {
    @Id
    private ObjectId id;
    @DBRef
    private Location location;
    private Date saleDate;
    private Date deliveryDate;

    public Sales(ObjectId id, Location location, Date saleDate, Date deliveryDate) {
        this.id = checkNotNull(id, "id can't be null");
        this.location = checkNotNull(location, "location can't be null");
        this.saleDate = checkNotNull(saleDate, "saleDate can't be null");
        this.deliveryDate = checkNotNull(deliveryDate, "deliveryDate can't be null");
    }

    public ObjectId getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", location=" + location +
                ", saleDate=" + saleDate +
                ", deliveryDate=" + deliveryDate + 
                '}';
    }
}