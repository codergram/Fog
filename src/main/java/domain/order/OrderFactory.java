package domain.order;

import domain.order.exceptions.OrderException;

public interface OrderFactory {

    Order createNewOrder(Order order) throws OrderException;

}
