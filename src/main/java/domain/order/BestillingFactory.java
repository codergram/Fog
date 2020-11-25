package domain.order;

import infrastructure.DBException;

public interface BestillingFactory {

    Order createBestilling(Order order) throws DBException;

}
