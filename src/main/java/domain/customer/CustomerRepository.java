package domain.customer;

import infrastructure.exceptions.DBException;

import java.util.List;

public interface CustomerRepository extends CustomerFactory {
    
    List<Customer> getAllCustomers() throws DBException;
    
}
