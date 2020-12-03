package domain.carport;

import domain.carport.shed.Shed;
import domain.partslist.Part;
import domain.partslist.Partslist;

public class Carport {
//    public enum Roof {
//        Flat,
//        Peak
//    }

    private final int id;
    private final double length;
    private final double width;
//    private final Enum<Roof> roofType;
    private final String roofType;
    private final Shed shed;
    private double price;
    private final Partslist partslist;
    
    public Carport(int id, double length, double width, String roofType, Shed shed, Partslist partslist) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.roofType = roofType;
        this.shed = shed;
        this.partslist = partslist;
        this.price = calculatePrice(partslist);
    }

    public Carport(double length, double width, String roofType, Shed shed, Partslist partslist) {
        this.id = -1;
        this.length = length;
        this.width = width;
        this.roofType = roofType;
        this.shed = shed;
        this.partslist = partslist;
        this.price = calculatePrice(partslist);
    }

    public Carport(double length, double width, String roofType, Shed shed) {
        this.id = -1;
        this.length = length;
        this.width = width;
        this.roofType = roofType;
        this.shed = shed;
        this.partslist = null;
        this.price = calculatePrice(partslist);
    }
    
    private double calculatePrice(Partslist partslist) {
        double x = 0.0;
        //TODO: Implement partslist
        //for(Part p: partslist.getMaterialList()){
        //  x += p.getPrice();
        //}
        return x;
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
        return roofType;
    }
    
    public boolean hasShed(){
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
    
    public Partslist getListOfItems() {
        return partslist;
    }
}
