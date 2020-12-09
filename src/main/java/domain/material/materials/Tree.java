package domain.material.materials;

public class Tree extends Material {
    
    public enum Type {
        Poles,
        Boards,
        Laths,
        Rules,
        Rafts,
        Roofing
    }
    
    private double length;
    private final Enum<Type> type;
    
    public Tree(int id, String name, double length, double price, Enum<Usage> usage, Enum<Type> type, Enum<Unit> unit) {
        super(id, name, price, usage, unit);
        this.length = length;
        this.type = type;
    }
    
    public Tree(String name, double length, double price, Enum<Usage> usage, Enum<Type> type, Enum<Unit> unit) {
        this(- 1, name, length, price, usage, type, unit);
        
    }
    
    public Tree(int id, String name, double price, Enum<Usage> usage, Enum<Type> type, Enum<Unit> unit) {
        this(id, name, 0.0, price, usage, type, unit);
    }
    
    public double getLength() {
        return length;
    }
    
    public Enum<Type> getType() {
        return type;
    }
    
    public String getLengthString() {
        return String.format("%.0f mm", length);
    }
    
    public void setLength(double length) {
        this.length = length;
    }
    
    @Override
    public String toString() {
        return "Tree{" +
                "length=" + length +
                ", type=" + type +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", usage=" + usage +
                ", unit=" + unit +
                '}';
    }
    
    @Override
    public String typeName() {
        return this.getClass().getSimpleName();
    }
}
