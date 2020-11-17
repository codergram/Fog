package api;

import domain.items.InvalidItem;
import domain.items.Item;
import domain.items.ItemNotFound;
import domain.items.ItemRepository;
import infrastructure.DBItems;

public class Webapp implements ItemRepository {
    private final static int VERSION = 1;
    private final static String TITLE = "WebApp Template";
    
    private final DBItems dbItems;
    
    public Webapp() {
        dbItems = new DBItems();
    }
    
    public static int getVersion() {
        return VERSION;
    }
    
    public static String getTitle() {
        return TITLE;
    }
    
    public Item findItem(int id) throws ItemNotFound {
        try{
            return dbItems.findItem(id);
        } catch (ItemNotFound e){
            throw new ItemNotFound(e.getMessage());
        }
    }
    
    public Iterable<Item> findAllItems() throws ItemNotFound {
        try {
            return dbItems.findAllItems();
        } catch (ItemNotFound e){
            throw new ItemNotFound(e.getMessage());
        }
    }
    
    public Item createItem(String itemName) throws InvalidItem {
        try {
            return dbItems.createItem(itemName);
        } catch (InvalidItem e){
            throw new InvalidItem();
        }
    }
}
