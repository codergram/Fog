package domain.items;

public class ItemNotFound extends Exception {
    public ItemNotFound(int id){
        super("Item with ID (" + id + ") not found!");
    }
    
    public ItemNotFound(String message) {
        super(message);
    }
}
