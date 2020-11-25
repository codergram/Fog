package domain.partslist;

import domain.material.materials.Material;

public class Part {
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
