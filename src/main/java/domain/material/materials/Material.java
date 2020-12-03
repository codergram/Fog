package domain.material.materials;

public abstract class Material {
//public class Material {
//
//    private final int id;
//    private final String name;
//    private final String category;
//    private final double length;
//    private final double price;
//    private final String unit;
//    private final String usage;
//    private final String type;
//
//    public Material(int id, String name, double length, double price, String unit, String usage, String type) {
//        this.id = id;
//        this.name = name;
//        this.length = length;
//        this.price = length * price;
//        this.unit = unit;
//        this.usage = usage;
//        this.type = type;
//        this.category = "Tree";
//    }
//
//    public Material(String name, double length, double price, String unit, String usage, String type) {
//        this.id = -1;
//        this.name = name;
//        this.length = length;
//        this.price = length * price;
//        this.unit = unit;
//        this.usage = usage;
//        this.type = type;
//        this.category = "Tree";
//    }
//
//    public Material(int id, String name, double price, String unit, String usage, String type) {
//        this.id = id;
//        this.name = name;
//        this.length = 0;
//        this.price = price;
//        this.unit = unit;
//        this.usage = usage;
//        this.type = type;
//        this.category = "Options";
//    }
//
//    public Material(String name, double price, String unit, String usage, String type) {
//        this.id = -1;
//        this.name = name;
//        this.length = 0;
//        this.price = price;
//        this.unit = unit;
//        this.usage = usage;
//        this.type = type;
//        this.category = "Options";
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public String getUnit() {
//        return unit;
//    }
//
//    public double getLength() {
//        return length;
//    }
//
//    public String getUsage() {
//        return usage;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    @Override
//    public String toString() {
//        return "Material{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", category='" + category + '\'' +
//                ", length=" + length +
//                ", price=" + price +
//                ", usage='" + usage + '\'' +
//                ", type='" + type + '\'' +
//                '}';
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
        public enum Unit {
            Stk,
            Pk,
            Sæt
}

        public enum Usage {
        //Flat roof
        UnderStarFronBack,
        UnderStarSide,
        OverStarFronBack,
        OverStarSide,
        Door,
        ShedGables,
        ShedSide,
        Straps,
        ShedStraps,
        Roof,
        CustomRoof,
        Foundation,
        ShedCladding,
        WaterStarSide,
        WaterStartEnd,
            RoffingSmall,
        RoffingBig,
        FoffingSmall,
        Windshield,
        StarSide,
        ShedStarSide,
        GablesCladding,
        WindshieldsBoard,
        WindshieldsGavel,
        Crossings,
        RaftersRight,
        RaftersLeft,
        StarWaterBoard,
        UniversalFittings,
        Pole,
        InnerCladding,
            InnerCladding300,
            InnerCladding350,
        OuterCladding,
            OuterCladding200,
            OuterCladding400,
        Shed,
        RoofBattens,
        TopLath,
        Rafters,
        BackStone,
        RoofTiles,
        Star,
            TopLayer
    }

    protected final int id;
    protected final String name;
    protected final double price;
    protected final Enum<Usage> usage;
    protected final Enum<Unit> unit;

    public Material(int id, String name, double price, Enum<Usage> usage, Enum<Unit> unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.usage = usage;
        this.unit = unit;
    }

    public Material(String name, double price, Enum<Usage> usage, Enum<Unit> unit) {
        this.id = -1;
        this.name = name;
        this.price = price;
        this.usage = usage;
        this.unit = unit;
    }

    public Enum<Material.Usage> getUsage() {
        return usage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public Enum<Unit> getUnit() {
        return unit;
    }
    
    public String getUnitString(){
        return unit.name();
    }
    
    public abstract String toString();
}
