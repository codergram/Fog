package domain.material.materials;

public class Generic extends Material {

    public Generic(int id, String name, double price, Material.Unit unit) {
        super(id, name, price, null, unit);
    }
    
    
    @Override
    public String toString() {
        return "Generic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", usage=" + usage +
                ", unit=" + unit.name() +
                '}';
    }
    
    @Override
    public String typeName() {
        return this.getClass().getSimpleName();
    }
}
