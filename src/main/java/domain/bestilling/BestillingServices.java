package domain.bestilling;

import infrastructure.DBexception;

public interface BestillingServices {

    void deleteBestillingById(int bes_id) throws DBexception;

    void updatedBestillingToAfventer(int bes_id) throws DBexception;

    void updatedBestillingToLeveret(int bes_id) throws DBexception;

}
