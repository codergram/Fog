package domain.materiel.materials;

public class Options extends Material {
    
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
