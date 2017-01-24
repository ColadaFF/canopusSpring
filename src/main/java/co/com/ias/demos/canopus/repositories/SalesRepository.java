package co.com.ias.demos.canopus.repositories;

import co.com.ias.demos.canopus.domain.Sale;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesRepository extends MongoRepository<Sale, ObjectId>{
    
}
