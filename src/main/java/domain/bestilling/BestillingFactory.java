package domain.bestilling;

import core.Order;
import infrastructure.DBexception;

public interface BestillingFactory {

    Order createBestilling(Order order) throws DBexception;

}
