package domain.stykliste;

import domain.material.materials.Material;

import java.util.LinkedList;
import java.util.List;

public class ListOfItems {
    private final List<Item> materialList;
    
    public ListOfItems(){
        materialList = new LinkedList<>();
    }
    
    public void addItem(Item item){
        if(!materialList.contains(item)){
            materialList.add(item);
        }
    }
    
    public void removeItemByMaterial(Material material){
        materialList.removeIf(i -> i.getMaterial().getId() == material.getId()
                && i.getMaterial().getName().equalsIgnoreCase(material.getName()));
    }
    
    public void removeItem(int index){
        materialList.remove(index);
    }
    
    public List<Item> getMaterialList() {
        return materialList;
    }
    
    public Item getItem(int index){
        return materialList.get(index);
    }
}

class Item {
    private final Material material;
    private final int amount;
    private final double price;
    
    public Item(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.price = material.getPrice() * amount;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public double getPrice() {
        return price;
    }
}
