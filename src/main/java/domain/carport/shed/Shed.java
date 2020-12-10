/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.carport.shed;

public class Shed {
  private final double length;
  private final double width;

  public Shed(double length, double width) {
    this.length = length;
    this.width = width;
  }

  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }

  @Override
  public String toString() {
    return "Shed{" + "length=" + length + ", width=" + width + '}';
  }
}
