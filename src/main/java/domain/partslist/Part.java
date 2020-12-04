package domain.partslist;

import domain.material.materials.Material;

public class Part {
        private final Material material;
        private final int amount;
        private final double price;
        private final String description;
        
        public Part(Material material, int amount, String description) {
            this.material = material;
            this.amount = amount;
            this.price = 0.0; //TODO: Fix nullpointer
            this.description = description;
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
    
        public String getDescription() {
            return description;
        }
}
