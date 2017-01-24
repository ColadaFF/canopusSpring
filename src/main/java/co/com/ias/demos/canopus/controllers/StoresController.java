package co.com.ias.demos.canopus.controllers;

import co.com.ias.demos.canopus.domain.Store;
import co.com.ias.demos.canopus.services.StoreServices;
import co.com.ias.demos.canopus.specification.StoreSpecification;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkArgument;

@CrossOrigin(value = "*", origins = "*")
@RestController(value = "stores")
@RequestMapping(value = "/stores")
public class StoresController implements StoreSpecification {

    private final StoreServices storeServices;

    @Autowired
    public StoresController(StoreServices storeServices) {
        this.storeServices = storeServices;
    }


    @Override
    public CompletableFuture<List<Store>> getStores() {
        CompletableFuture<List<Store>> listCompletableFuture = new CompletableFuture<>();
        storeServices.getAllStores()
                .toList()
                .subscribe(new SingleObserver<List<Store>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Subscribed");
                    }

                    @Override
                    public void onSuccess(List<Store> stores) {
                        listCompletableFuture.complete(stores);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listCompletableFuture.obtrudeException(e);
                    }
                });
        return listCompletableFuture;
    }

    @Override
    public CompletableFuture<Store> getStore(@PathVariable String storeId) {
        checkArgument(ObjectId.isValid(storeId), "StoreId is not valid");
        CompletableFuture<Store> completableFuture = new CompletableFuture<>();
        storeServices.getStore(new ObjectId(storeId))
                .subscribe(new MaybeObserver<Store>() {
                    Store storeVal = null;

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Subscribed");
                    }

                    @Override
                    public void onSuccess(Store store) {
                        storeVal = store;
                    }

                    @Override
                    public void onError(Throwable e) {
                        completableFuture.obtrudeException(e);
                    }

                    @Override
                    public void onComplete() {
                        completableFuture.complete(storeVal);
                    }
                });
        return completableFuture;
    }

    @Override
    public CompletableFuture<List<Store>> getStores(String zoneId) {
        checkArgument(ObjectId.isValid(zoneId), "zoneId is not valid");
        CompletableFuture<List<Store>> completableFuture = new CompletableFuture<>();
        storeServices.getStores(new ObjectId(zoneId))
                .toList()
                .subscribe(new SingleObserver<List<Store>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Subscribed");
                    }

                    @Override
                    public void onSuccess(List<Store> stores) {
                        completableFuture.complete(stores);
                    }

                    @Override
                    public void onError(Throwable e) {
                        completableFuture.obtrudeException(e);
                    }
                });
        return completableFuture;
    }

    @Override
    public CompletableFuture<Store> createStore(@RequestBody Store store) {
        CompletableFuture<Store> completableFuture = new CompletableFuture<>();
        storeServices.createStore(store)
                .subscribe(new SingleObserver<Store>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Subscribed");
                    }

                    @Override
                    public void onSuccess(Store store) {
                        completableFuture.complete(store);
                    }

                    @Override
                    public void onError(Throwable e) {
                        completableFuture.obtrudeException(e);
                    }
                });
        return completableFuture;
    }
}
