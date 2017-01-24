package co.com.ias.demos.canopus.repositories;

import co.com.ias.demos.canopus.domain.Zone;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ZonesRepository extends MongoRepository<Zone, ObjectId> {
}
