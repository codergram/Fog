package domain.order;

import infrastructure.DBException;

public interface OrderServices {

    void deleteBestillingById(int bes_id) throws DBException;

    void updatedBestillingToAfventer(int bes_id) throws DBException;

    void updatedBestillingToLeveret(int bes_id) throws DBException;

}
