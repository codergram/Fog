package domain.kunde;

import core.Customer;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface KundeRepository {

    ArrayList<Customer> getAllKunderFromDB() throws DBexception;

    Customer getKundeById(int kunde_id) throws DBexception;

}
