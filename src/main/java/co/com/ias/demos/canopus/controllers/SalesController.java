package co.com.ias.demos.canopus.controllers;

import co.com.ias.demos.canopus.domain.Sale;
import co.com.ias.demos.canopus.services.SalesServices;
import co.com.ias.demos.canopus.specification.SalesSpecification;
import static com.google.common.base.Preconditions.checkArgument;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.google.common.base.Preconditions.checkArgument;

@CrossOrigin(value = "*", origins = "*")
@RestController(value = "sales")
@RequestMapping(value = "/sales")
public class SalesController implements SalesSpecification {

    private final SalesServices salesServices;

    @Autowired
    public SalesController(SalesServices salesServices) {
        this.salesServices = salesServices;
    }

    @Override
    public CompletableFuture<List<Sale>> getSales() {
        CompletableFuture<List<Sale>> listCompletedFuture = new CompletableFuture<>();
        salesServices.getAllSales()
                .toList()
                .subscribe(new SingleObserver<List<Sale>>() {
                    @Override
                    public void onSubscribe(Disposable dspsbl) {
                        System.out.println("Susbcribe");
                    }

                    @Override
                    public void onSuccess(List<Sale> sales) {
                        listCompletedFuture.complete(sales);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listCompletedFuture.obtrudeException(e);
                    }
                });
        return listCompletedFuture;
    }

    @Override
    public CompletableFuture<Sale> getSale(String saleId) {
        checkArgument(ObjectId.isValid(saleId), "saleId is not valid");
        CompletableFuture<Sale> completableFuture = new CompletableFuture<>();
        salesServices.getSale(new ObjectId(saleId))
                .subscribe(new MaybeObserver<Sale>() {
                    Sale sale = null;

                    @Override

                    public void onSubscribe(Disposable dspsbl) {
                        System.out.println("Subscribe");
                    }

                    @Override
                    public void onSuccess(Sale t) {
                        sale = t;
                    }

                    @Override
                    public void onError(Throwable e) {
                        completableFuture.obtrudeException(e);
                    }

                    @Override
                    public void onComplete() {
                        completableFuture.complete(sale);
                    }
                });
        return completableFuture;
    }

    @Override
    public CompletableFuture<List<Sale>> getSalesByStoreId(String[] stores, String deliveryDate) {
        checkArgument(ObjectId.isValid(deliveryDate), "deliveryDate is not valid");
        CompletableFuture<List<Sale>> completableFuture = new CompletableFuture<>();
        
        return completableFuture;
    }

}
