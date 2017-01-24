package co.com.ias.demos.canopus.repositories;

import co.com.ias.demos.canopus.domain.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonsRepository extends MongoRepository<Person, ObjectId> {
}
