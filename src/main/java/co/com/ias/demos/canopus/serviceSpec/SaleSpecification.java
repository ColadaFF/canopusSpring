package co.com.ias.demos.canopus.serviceSpec;

import co.com.ias.demos.canopus.domain.Sale;
import co.com.ias.demos.canopus.domain.Store;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.bson.types.ObjectId;

public interface SaleSpecification {
    
    Observable<Store> getStoresBySaleDate(String[] stores, String date);

    Observable<Sale> getAllSales();

    Maybe<Sale> getSale(ObjectId saleId);

    Single<Sale> createSale(Sale sale);
    
}
