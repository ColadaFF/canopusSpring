package co.com.ias.demos.canopus.controllers;

import co.com.ias.demos.canopus.domain.Store;
import co.com.ias.demos.canopus.specification.StoreSpecification;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@CrossOrigin(value = "*", origins = "*")
@RestController(value = "stores")
@RequestMapping(value = "/stores")
public class StoresController implements StoreSpecification{



    @Override
    public Future<List<Store>> getStores() {
       Future<List<Store>> listFuture = new CompletableFuture<>();

       return listFuture;
    }

    @Override
    public Future<Store> getStore(@PathVariable String storeId) {
        return null;
    }

    @Override
    public Future<List<Store>> getStores(ObjectId zoneId) {
        return null;
    }

    @Override
    public Future<Store> createStore(@RequestBody Store store) {
        return null;
    }
}
