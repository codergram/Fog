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
