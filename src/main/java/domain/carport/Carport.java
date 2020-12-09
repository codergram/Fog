package domain.carport;

import domain.carport.shed.Shed;
import domain.partslist.Part;
import domain.partslist.Partslist;

public class Carport {
  public enum Roof {
    Flat,
    Peak
  }

  private final int id;
  private final double length;
  private final double width;
  private final Enum<Roof> roofType;
  private final Shed shed;
  private double price;
  private Partslist partslist;

  public Carport(
      int id, double length, double width, Enum<Roof> roofType, Shed shed, Partslist partslist) {
    this.id = id;
    this.length = length;
    this.width = width;
    this.roofType = roofType;
    this.shed = shed;
    this.partslist = partslist;
    this.price = calculatePrice(partslist);
  }

  public Carport(
      int id,
      double length,
      double width,
      Enum<Roof> roofType,
      Shed shed,
      Partslist partslist,
      double price) {
    this.id = id;
    this.length = length;
    this.width = width;
    this.roofType = roofType;
    this.shed = shed;
    this.partslist = partslist;
    this.price = price;
  }

  public Carport(double length, double width, Enum<Roof> roofType, Shed shed, Partslist partslist) {
    this.id = -1;
    this.length = length;
    this.width = width;
    this.roofType = roofType;
    this.shed = shed;
    this.partslist = partslist;
    this.price = calculatePrice(partslist);
  }

  public Carport(double length, double width, Enum<Roof> roofType, Shed shed) {
    this.id = -1;
    this.length = length;
    this.width = width;
    this.roofType = roofType;
    this.shed = shed;
    this.partslist = null;
    this.price = 0.0;
  }

  private double calculatePrice(Partslist partslist) {
    double x = 0.0;
    for (Part p : partslist.getPartList()) {
      x += p.getPrice();
    }
    return x;
  }

  public Partslist getPartslist() {
    return partslist;
  }

  public void setPartslist(Partslist partslist) {
    this.partslist = partslist;
    this.price = calculatePrice(partslist);
  }

  public int getId() {
    return id;
  }

  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }

  public Enum<Roof> getRoofType() {
    return this.roofType;
  }

  public boolean hasShed() {
    return shed != null;
  }

  public Shed getShed() {
    return shed;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
