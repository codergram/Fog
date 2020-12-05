package domain.partslist;

import domain.material.materials.Material;

public class Part {
        private final Material material;
        private final int amount;
        private final double price;
        private final String description;
        
        public Part(Material material, int amount, String description) {
            if(material != null) {
                this.material = material;
                this.amount = amount;
                this.price = material.getPrice() * amount;
                this.description = description;
            } else {
                this.material = material;
                this.amount = amount;
                this.price = 0.0;
                this.description = description;
            }
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
    
    @Override
    public String toString() {
        return "Part{" +
                "material=" + material +
                ", amount=" + amount +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
