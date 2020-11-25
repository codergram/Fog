package infrastructure;

public class DBException extends Exception {
    
    public DBException() {
        System.out.println("Database error");
    }
    
    public DBException(String message) {
        super(message);
    }
}
