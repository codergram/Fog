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
