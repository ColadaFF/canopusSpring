package co.com.ias.demos.canopus.controllers;

import co.com.ias.demos.canopus.domain.Employee;
import co.com.ias.demos.canopus.services.EmployeeServices;
import co.com.ias.demos.canopus.specification.EmployeeSpecification;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@CrossOrigin(value = "*", origins = "*")
@RestController(value = "employees")
@RequestMapping(value = "/employees")
public class EmployeesController implements EmployeeSpecification {

    private final EmployeeServices employeeServices;

    @Autowired
    public EmployeesController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @Override
    public CompletableFuture<List<Employee>> getEmployees() {
        CompletableFuture<List<Employee>> listCompletableFuture = new CompletableFuture<>();
        employeeServices.getEmployees()
                .toList()
                .subscribe(new SingleObserver<List<Employee>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Subscribed");
                    }

                    @Override
                    public void onSuccess(List<Employee> employees) {
                        listCompletableFuture.complete(employees);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listCompletableFuture.obtrudeException(e);
                    }
                });
        return listCompletableFuture;
    }

    @Override
    public CompletableFuture<Employee> getEmployee(@PathVariable String employeeId) {
        CompletableFuture<Employee> employeeCompletableFuture = new CompletableFuture<>();
        employeeServices.getEmployee(employeeId)
                .subscribe(new MaybeObserver<Employee>() {
                    Employee employeeToReturn;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Employee employee) {
                        employeeToReturn = employee;
                    }

                    @Override
                    public void onError(Throwable e) {
                        employeeCompletableFuture.obtrudeException(e);
                    }

                    @Override
                    public void onComplete() {
                        employeeCompletableFuture.complete(employeeToReturn);
                    }
                });
        return employeeCompletableFuture;
    }
}
