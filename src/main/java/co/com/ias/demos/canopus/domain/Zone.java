package co.com.ias.demos.canopus.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "zones")
public class Zone {
    @Id
    private ObjectId id;
    private String name;
    @DBRef
    private List<Store> stores;

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Store> getStores() {
        return stores;
    }
}
