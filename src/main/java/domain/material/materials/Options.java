package domain.material.materials;

public class Options extends Material {
    
    public enum Type {
        BottomScrews,
        PerforatedTape,
        Universal,
        Screw,
        FittingScrews,
        BoardBolt,
        SquareDiscs,
        RoofBinder,
        BarnDoorHandle,
        Hinge,
        AngleBracket,
        Dobbelt,
        BackStone,
        TopLayer,
        BackstoneFittings,
        RoofBinders,
        Rafters
    }
    
    private final Enum<Type> type;
    
    public Options(int id, String name, double price, Enum<Usage> usage, Enum<Type> type, Enum<Unit> unit) {
        super(id, name, price, usage, unit);
        this.type = type;
    }
    
    public Options(String name, double price, Enum<Usage> usage, Enum<Type> type, Enum<Unit> unit) {
        super(name, price, usage, unit);
        this.type = type;
    }
    
    public Enum<Type> getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "Options{" +
                "type=" + type +
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
