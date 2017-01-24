package co.com.ias.demos.canopus.services;

import co.com.ias.demos.canopus.domain.Employee;
import co.com.ias.demos.canopus.domain.Person;
import co.com.ias.demos.canopus.repositories.EmployeesRepository;
import co.com.ias.demos.canopus.repositories.PersonsRepository;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class EmployeeServices {
    private EmployeesRepository employeesRepository;
    private PersonsRepository personsRepository;
    private final MongoTemplate mongoTemplate;


    @Autowired
    public EmployeeServices(EmployeesRepository employeesRepository, PersonsRepository personsRepository, MongoTemplate mongoTemplate) {
        this.employeesRepository = employeesRepository;
        this.personsRepository = personsRepository;
        this.mongoTemplate = mongoTemplate;
    }


    public Observable<Employee> getEmployees() {
        return Observable.fromIterable(employeesRepository.findAll());
    }


    public Maybe<Employee> getEmployee(String id) {
        return Maybe.create(e -> {
            Person person = mongoTemplate.findOne(Query.query(where("identification").is(id)), Person.class);
            Optional.ofNullable(person)
                    .map(person1 -> mongoTemplate.findOne(Query.query(where("person").is(person1.getId())), Employee.class))
                    .ifPresent(e::onSuccess);
            e.onComplete();
        });
    }

}
