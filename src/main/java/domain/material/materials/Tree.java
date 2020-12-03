package domain.material.materials;

import java.util.List;

public class Tree extends Material {
    
    public static final List<Usage> usages = List.of(Usage.Pole, Usage.Raft);
    
    public enum Type {
        Pole,
        Board
    }

    private final double length;
    private final Type type;
    
    public Tree(int id, String name, int length, double price, Usage usage) {
        super(id, name, price, usage);
        this.length = length;
        if (usages.contains(usage)) {
            throw  new IllegalStateException("");
        }
    }
    public Tree(String name, int length, double price) {
        super(name, price);
        this.length = length;
    }
    
    @Override
    public Tree asType(Type type) throws NotMyTypeExecption {
        if (isType(type)) {
            return this;
        } else {
            throw new NotMyTypeExecption();
        }
    }
    
    public double getLength() {
        return length;
    }
    
    @Override
    public boolean isType(Type type) {
        return type == this.type;
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
