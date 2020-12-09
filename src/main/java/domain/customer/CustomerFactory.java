package domain.customer;

import infrastructure.exceptions.DBException;

public interface CustomerFactory {
    
    Customer createCustomer(Customer customer) throws DBException;
    
}
