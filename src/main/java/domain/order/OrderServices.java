package domain.order;

import infrastructure.DBException;

public interface OrderServices {

    void deleteBestillingById(int bes_id) throws DBException;

    void updatedOrderStatus(int orderId, Order.Status status) throws DBException;

}
