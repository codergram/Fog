package core.materialer;

public class Options extends Materiel{
    
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
