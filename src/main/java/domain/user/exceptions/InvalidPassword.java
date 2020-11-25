package domain.user.exceptions;

public class InvalidPassword extends Exception{
   public InvalidPassword() {
        super("Passwords do not match.");
    }
}
