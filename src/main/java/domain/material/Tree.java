/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.material;

public class Tree extends Material {

  private final Enum<Type> type;
  private double length;
  public Tree(
      int id,
      String name,
      double length,
      double price,
      Enum<Usage> usage,
      Enum<Type> type,
      Enum<Unit> unit) {
    super(id, name, price, usage, unit);
    this.length = length;
    this.type = type;
  }

  public Tree(
      String name,
      double length,
      double price,
      Enum<Usage> usage,
      Enum<Type> type,
      Enum<Unit> unit) {
    this(-1, name, length, price, usage, type, unit);
  }

  public Tree(
      int id, String name, double price, Enum<Usage> usage, Enum<Type> type, Enum<Unit> unit) {
    this(id, name, 0.0, price, usage, type, unit);
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public Enum<Type> getType() {
    return type;
  }

  public String getLengthString() {
    return String.format("%.0f mm", length);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Tree)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    Tree tree = (Tree) o;

    return type != null ? type.equals(tree.type) : tree.type == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (type != null ? type.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Tree{"
        + "length="
        + length
        + ", type="
        + type
        + ", id="
        + id
        + ", name='"
        + name
        + '\''
        + ", price="
        + price
        + ", usage="
        + usage
        + ", unit="
        + unit
        + '}';
  }

  @Override
  public String typeName() {
    return this.getClass().getSimpleName();
  }

  public enum Type {
    Poles,
    Boards,
    Laths,
    Rules,
    Rafts,
    Roofing
  }
}
