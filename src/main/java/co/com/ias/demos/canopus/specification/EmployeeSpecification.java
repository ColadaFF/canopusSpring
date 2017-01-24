package co.com.ias.demos.canopus.specification;

import co.com.ias.demos.canopus.domain.Employee;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeSpecification {
    @Async
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<List<Employee>> getEmployees();

    @Async
    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<Employee> getEmployee(@PathVariable String employeeId);
}
