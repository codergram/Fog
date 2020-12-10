/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.order.exceptions;

public class OrderNotFound extends Exception {
  public OrderNotFound() {
    super("Order not found");
  }

  public OrderNotFound(String message) {
    super(message);
  }
}
