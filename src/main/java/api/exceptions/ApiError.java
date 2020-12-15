/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package api.exceptions;

public class ApiError extends Exception {

  public ApiError(Throwable cause) {
    super(cause);
  }

  public ApiError(String message) {
    super(message);
  }
}
