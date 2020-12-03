package domain.material.materials;

public abstract class Material {
    
    public enum Usage {
        Roof,
        Raft,
        Pole
    }

    protected final int id;
    protected final String name;
    protected final double price;
    protected final Usage usage;
    
    public Material(int id, String name, double price, Usage usage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.usage = usage;
    }
    
    public Material(String name, double price) {
        this(- 1, name, price, Usage.Roof);
    }
    
    public  boolean isType(Tree.Type type) { return false; }
    public  boolean isType(Options.Type type) { return  false;}
    
    public Tree asType(Tree.Type type) throws NotMyTypeExecption { throw new NotMyTypeExecption(); }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public abstract String toString();
}
