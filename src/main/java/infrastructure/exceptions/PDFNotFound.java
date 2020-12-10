/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package infrastructure.exceptions;

public class PDFNotFound extends Exception {
  public PDFNotFound() {}

  public PDFNotFound(String message) {
    super(message);
  }

  public PDFNotFound(String message, Throwable cause) {
    super(message, cause);
  }

  public PDFNotFound(Throwable cause) {
    super(cause);
  }

  public PDFNotFound(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
