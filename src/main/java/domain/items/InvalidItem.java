package domain.items;

public class InvalidItem extends Exception {
    public InvalidItem() {
        super("Invalid item!");
    }
}
