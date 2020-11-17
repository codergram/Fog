package domain.items;

public interface ItemRepository extends ItemFactory {
    Item findItem(int id) throws ItemNotFound;
    Iterable<Item> findAllItems() throws ItemNotFound;
    
}
