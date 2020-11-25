package domain.carport;

import domain.carport.shed.Shed;
import domain.stykliste.ListOfItems;

public class Carport {
    public enum Roof {
        Flat,
        Sloping
    }

    private final int id;
    private final double length;
    private final double width;
    private final Enum<Roof> roofType;
    private final Shed shed;
    private double price;
    private final ListOfItems listOfItems;
    
    public Carport(int id, double length, double width, Enum<Roof> roofType, Shed shed, ListOfItems listOfItems) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.roofType = roofType;
        this.shed = shed;
        this.listOfItems = listOfItems;
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
    
    public String getRoofType() {
        return roofType.name();
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
    
    public ListOfItems getListOfItems() {
        return listOfItems;
    }
}
