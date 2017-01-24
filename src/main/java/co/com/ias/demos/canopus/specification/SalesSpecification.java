package co.com.ias.demos.canopus.specification;

import co.com.ias.demos.canopus.domain.Sale;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface SalesSpecification {
    
    @Async
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<List<Sale>> getSales();
    
    @Async
    @RequestMapping(value = "/{salesId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<Sale> getSale(@PathVariable String saleId);
    
    @Async
    @RequestMapping(value = "/{deliveryDate}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<List<Sale>> getSalesByStoreId(@RequestParam( value= "store[]" ) String[] stores, 
            @PathVariable String deliveryDate);
    
}
