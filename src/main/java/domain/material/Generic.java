/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.material;

public class Generic extends Material {

  public Generic(int id, String name, double price, Material.Unit unit) {
    super(id, name, price, null, unit);
  }

  @Override
  public String toString() {
    return "Generic{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", price="
        + price
        + ", usage="
        + usage
        + ", unit="
        + unit.name()
        + '}';
  }

  @Override
  public String typeName() {
    return this.getClass().getSimpleName();
  }
}
