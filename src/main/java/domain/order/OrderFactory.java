package domain.order;

import infrastructure.DBException;

public interface OrderFactory {

    Order createBestilling(Order order) throws DBException;

}
