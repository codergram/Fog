package domain.order;

import infrastructure.DBException;

public interface BestillingServices {

    void deleteBestillingById(int bes_id) throws DBException;

    void updatedBestillingToAfventer(int bes_id) throws DBException;

    void updatedBestillingToLeveret(int bes_id) throws DBException;

}
