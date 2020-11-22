package domain.bestilling;

import core.Bestilling;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface BestillingRepository {

    ArrayList<Bestilling> getAllBestillingFromDB() throws DBexception;

    Bestilling getBestillingById(int bes_id) throws DBexception;

}
