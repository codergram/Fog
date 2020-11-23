package core.materialer;

public class Tree extends Materiel{

    private final double length;
    
    public Tree(int id, String name, int length, double price) {
        super(id, name, price);
        this.length = length;
    }
    public Tree(String name, int length, double price) {
        super(name, price);
        this.length = length;
    }
    
    public double getLength() {
        return length;
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
