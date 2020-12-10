/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.order;

import domain.order.exceptions.OrderException;
import domain.order.exceptions.OrderNotFound;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends OrderFactory {

  int getOrderNumberFromUUID(UUID uuid);

  List<Order> getAllOrders() throws OrderNotFound;

  Order getOrderById(int id) throws OrderNotFound;

  void updateOrderStatusById(int id, Order.Status status) throws OrderException;

  void assignOrder(int ordrenummer, int userId) throws OrderNotFound;

  void updateMargin(int orderId, double newMargin) throws OrderException;

  void releaseOrder(int orderId) throws OrderNotFound;
}
