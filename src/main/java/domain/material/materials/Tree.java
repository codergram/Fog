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
        super(id, name, length * price, usage, unit);
        this.length = length;
        this.type = type;
    }
    public Tree(String name, double length, double price, Enum<Usage> usage, Enum<Type> type, Enum<Unit> unit) {
        super(name, length * price, usage, unit);
        this.length = length;
        this.type = type;

    }

    public double getLength() {
        return length;
    }

    public Enum<Type> getType() {
        return type;
    }
    
    public void setLength(double length) {
        this.length = length;
    }
    
    @Override
    public String toString() {
        return "Tree{" +
                "length=" + length +
                ", id=" + id +
                ", navn='" + name + '\'' +
                ", pris=" + price +
                '}';
    }
}
