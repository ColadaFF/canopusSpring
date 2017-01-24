package co.com.ias.demos.canopus.services;

import co.com.ias.demos.canopus.domain.Sale;
import co.com.ias.demos.canopus.domain.Store;
import co.com.ias.demos.canopus.repositories.SalesRepository;
import co.com.ias.demos.canopus.serviceSpec.SaleSpecification;
import static com.google.common.base.Preconditions.checkNotNull;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service()
public class SalesServices implements SaleSpecification {

    private MongoTemplate mongoTemplate;
    private SalesRepository salesRepository;

    @Autowired
    public SalesServices(MongoTemplate mongoTemplate, SalesRepository salesRepository) {
        this.mongoTemplate = mongoTemplate;
        this.salesRepository = salesRepository;
    }

    @Override
    public Observable<Sale> getAllSales() {
        return Observable.fromIterable(salesRepository.findAll());
    }

    @Override
    public Maybe<Sale> getSale(ObjectId saleId) {
        checkNotNull(saleId, "saleId can't be null");
        return Maybe.create(e -> {
            Sale sale = salesRepository.findOne(saleId);
            Optional.ofNullable(sale)
                    .ifPresent(e::onSuccess);
            e.onComplete();
        });
    }

    @Override
    public Single<Sale> createSale(Sale sale) {
        checkNotNull(sale, "sale can't be null");
        return Single.create(e -> {
            try {
                e.onSuccess(salesRepository.save(sale));
            } catch (Exception ex) {
                e.onError(ex);
            }
        });
    }

    @Override
    public Observable<Store> getStoresBySaleDate(String[] stores, String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateObj = sdf.parse(date);
            List<ObjectId> storesId = Arrays.asList(stores)
                    .stream()
                    .map(ObjectId::new)
                    .collect(Collectors.toList());
            
           List<Sale> sales = mongoTemplate.find(Query.query(Criteria.where("store").in(storesId).and("deliveryDate").is(dateObj)), Sale.class);
           return Observable.fromIterable(sales)
                   .map(Sale::getStore)
                   .distinct(Store::getId);
           
        } catch (ParseException e) {
            return Observable.error(e);
        }
    }
}
