package domain.material.materials;

public class Options extends Material {
    
    public enum Type{
        Screw,
        Fitting
    }
    
    public Options(int id, String name, double price) {
        super(id, name, price);
    }
    
    public Options(String name, double price) {
        super(name, price);
    }
    
    @Override
    public String toString() {
        return "Options{" +
                "id=" + id +
                ", navn='" + name + '\'' +
                ", pris=" + price +
                '}';
    }
}
