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
