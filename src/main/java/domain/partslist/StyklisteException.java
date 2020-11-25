package domain.partslist;

public class StyklisteException extends Exception{
    public StyklisteException() {
        super("Styklisten blev ikke fundet");
    }
    
    public StyklisteException(String message) {
        super(message);
    }
    
    public StyklisteException(Throwable cause) {
        super(cause);
    }
}
