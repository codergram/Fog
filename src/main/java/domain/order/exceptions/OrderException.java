/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.order.exceptions;

public class OrderException extends Exception {
  public OrderException() {
    super("Order error");
  }

  public OrderException(String message) {
    super(message);
  }

  public OrderException(Throwable cause) {
    super(cause);
  }
}
