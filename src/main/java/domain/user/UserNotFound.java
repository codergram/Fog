package domain.user;

public class UserNotFound extends Exception {
    public UserNotFound() {
        super("User was not found!");
    }
    
    public UserNotFound(String user) {
        super("User was not found: " + user);
    }
}
