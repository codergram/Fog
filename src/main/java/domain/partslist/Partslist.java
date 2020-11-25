package domain.partslist;

import domain.material.materials.Material;

import java.util.LinkedList;
import java.util.List;

public class Partslist {
    private final List<Part> materialList;
    
    public Partslist() {
        materialList = new LinkedList<>();
    }
    
    public void addItem(Part part) {
        if (! materialList.contains(part)) {
            materialList.add(part);
        }
    }
    
    public Part newPart(Material material, int amount) {
        return new Part(material, amount);
    }
    
    public void removeItemByMaterial(Material material) {
        materialList.removeIf(i -> i.getMaterial().getId() == material.getId()
                && i.getMaterial().getName().equalsIgnoreCase(material.getName()));
    }
    
    public void removeItem(int index) {
        materialList.remove(index);
    }
    
    public List<Part> getMaterialList() {
        return materialList;
    }
    
    public Part getItem(int index) {
        return materialList.get(index);
    }
    
    
    public static class Part {
        private final Material material;
        private final int amount;
        private final double price;
        
        public Part(Material material, int amount) {
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
}
