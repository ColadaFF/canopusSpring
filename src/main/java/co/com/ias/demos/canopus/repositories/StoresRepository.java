package co.com.ias.demos.canopus.repositories;

import co.com.ias.demos.canopus.domain.Store;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoresRepository extends MongoRepository<Store, ObjectId> {
}
