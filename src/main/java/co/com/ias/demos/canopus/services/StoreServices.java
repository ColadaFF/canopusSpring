package co.com.ias.demos.canopus.services;

import co.com.ias.demos.canopus.domain.Store;
import co.com.ias.demos.canopus.domain.Zone;
import co.com.ias.demos.canopus.repositories.StoresRepository;
import co.com.ias.demos.canopus.repositories.ZonesRepository;
import co.com.ias.demos.canopus.serviceSpec.StoreSpecification;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service()
public class StoreServices implements StoreSpecification {

    private final MongoTemplate mongoTemplate;
    private final StoresRepository storesRepository;
    private final ZonesRepository zonesRepository;

    @Autowired
    public StoreServices(MongoTemplate mongoTemplate, StoresRepository storesRepository, ZonesRepository zonesRepository) {
        this.mongoTemplate = mongoTemplate;
        this.storesRepository = storesRepository;
        this.zonesRepository = zonesRepository;
    }


    @Override
    public Observable<Store> getStores(ObjectId zoneId) {
        checkNotNull(zoneId, "zoneId can't be null");
        Optional<Zone> zoneOptional = Optional.ofNullable(zonesRepository.findOne(zoneId));
        return zoneOptional
                .map(zone -> Observable.fromIterable(zone.getStores()))
                .orElse(Observable.empty());
    }

    @Override
    public Observable<Store> getAllStores() {
        return Observable.fromIterable(storesRepository.findAll());
    }

    @Override
    public Maybe<Store> getStore(ObjectId storeId) {
        checkNotNull(storeId, "storeId can't be null");
        return Maybe.create(e -> {
            Store store = storesRepository.findOne(storeId);
            Optional.ofNullable(store)
                    .ifPresent(e::onSuccess);
            e.onComplete();
        });
    }

    @Override
    public Single<Store> createStore(Store store) {
        checkNotNull(store, "store can't be null");
        return Single.create(e -> {
            try {
                e.onSuccess(storesRepository.save(store));
            } catch (Exception ex) {
                e.onError(ex);
            }
        });
    }
}
