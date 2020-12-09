package domain.user.exceptions;

public class UserExists extends Exception {
  public UserExists(String message) {
    super("User already exists");
  }
}
