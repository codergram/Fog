package domain.items;

public interface ItemFactory {
    Item createItem(String itemName) throws InvalidItem;
}
