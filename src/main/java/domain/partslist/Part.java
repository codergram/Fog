package domain.partslist;

import domain.material.materials.Material;
import domain.material.materials.Tree;

public class Part {
  private final Material material;
  private final int amount;
  private final double price;
  private final String description;

  public Part(Material material, int amount, String description) {
    this.material = material;
    this.amount = amount;
    this.price = calculatePrice(material, amount);
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

  private double calculatePrice(Material material, int amount) {
    if (material instanceof Tree) {
      return (((Tree) material).getLength() / 100) * amount * material.getPrice();
    } else {
      return material.getPrice() * amount;
    }
  }

  @Override
  public String toString() {
    return "Part{"
        + "material="
        + material
        + ", amount="
        + amount
        + ", price="
        + price
        + ", description='"
        + description
        + '\''
        + '}';
  }
}
