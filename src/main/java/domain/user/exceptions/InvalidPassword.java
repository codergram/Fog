package domain.user.exceptions;

public class InvalidPassword extends Exception {
    public InvalidPassword() {
        System.out.println("Invalid password!");
    }
}
