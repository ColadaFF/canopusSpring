package co.com.ias.demos.canopus.serviceSpec;

import co.com.ias.demos.canopus.domain.Store;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.bson.types.ObjectId;

public interface StoreSpecification {
    Observable<Store> getStores(ObjectId zoneId);

    Observable<Store> getAllStores();

    Maybe<Store> getStore(ObjectId storeId);

    Single<Store> createStore(Store store);
}
