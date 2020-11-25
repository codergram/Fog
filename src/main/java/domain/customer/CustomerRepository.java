package domain.customer;

import infrastructure.exceptions.DBException;

import java.util.ArrayList;

public interface CustomerRepository extends CustomerFactory {

    ArrayList<Customer> getAllKunderFromDB() throws DBException;

    Customer getKundeById(int kunde_id) throws DBException;

}
