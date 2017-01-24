package co.com.ias.demos.canopus.specification;

import co.com.ias.demos.canopus.domain.Store;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.concurrent.Future;

public interface StoreSpecification {
    @Async
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Future<List<Store>> getStores();

    @Async
    @RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Future<Store> getStore(@PathVariable String storeId);

    @Async
    @RequestMapping(value = "/zone/{zoneId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Future<List<Store>> getStores(ObjectId zoneId);

    @Async
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    Future<Store> createStore(@RequestBody Store store);
}
