package co.com.ias.demos.canopus.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Document(collection = "employees")
public class Employee {
    @Id
    private ObjectId id;
    @DBRef
    private Person person;
    @DBRef
    private List<Zone> zones;

    public Employee(ObjectId id, Person person, List<Zone> zones) {
        this.id = checkNotNull(id, "id can't be null");
        this.person = checkNotNull(person, "person can't be null");
        this.zones = checkNotNull(zones, "zones can't be null");
    }

    public ObjectId getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public List<Zone> getZones() {
        return zones;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", person=" + person +
                ", zones=" + zones +
                '}';
    }
}
