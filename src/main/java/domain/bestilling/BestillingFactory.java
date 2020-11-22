package domain.bestilling;

import core.Bestilling;
import infrastructure.DBexception;

public interface BestillingFactory {

    Bestilling createBestilling(Bestilling bestilling) throws DBexception;

}
