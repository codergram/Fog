package domain.carport.shed;


import java.util.ArrayList;

public interface ShedRepository extends ShedFactory{

    ArrayList<Shed> getAllSheds() throws ShedException;

    Shed getShedByOrderId(int orderId) throws ShedException;

}
