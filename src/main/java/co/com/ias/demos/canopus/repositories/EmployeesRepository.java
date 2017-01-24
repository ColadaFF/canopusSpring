package co.com.ias.demos.canopus.repositories;

import co.com.ias.demos.canopus.domain.Location;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeesRepository extends MongoRepository<Location, ObjectId> {
}
