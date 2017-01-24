package co.com.ias.demos.canopus.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.google.common.base.Preconditions.checkNotNull;

@Document(collection = "persons")
public class Person {
    @Id
    private ObjectId id;
    private String name;
    private String lastName;
    private String identification;

    public Person(String name, String lastName, String identification, ObjectId id) {
        this.name = checkNotNull(name, "name can't be null");
        this.lastName = checkNotNull(lastName, "lastName can't be null");
        this.identification = checkNotNull(identification, "identification can't be null");
        this.id = checkNotNull(id, "id can't be null");
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public ObjectId getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identification='" + identification + '\'' +
                '}';
    }
}
