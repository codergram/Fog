/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.partslist.exceptions;

public class PartslistException extends Exception {
  public PartslistException() {
    super("Styklisten blev ikke fundet");
  }

  public PartslistException(String message) {
    super(message);
  }

  public PartslistException(Throwable cause) {
    super(cause);
  }
}
