package domain.customer;

import infrastructure.DBException;

import java.util.ArrayList;

public interface CustomerRepository {

    ArrayList<Customer> getAllKunderFromDB() throws DBException;

    Customer getKundeById(int kunde_id) throws DBException;

}
