package domain.material.materials;

//public abstract class Material {
public class Material {

    private final int id;
    private final String name;
    private final String category;
    private final double length;
    private final double price;
    private final String unit;
    private final String usage;
    private final String type;

    public Material(int id, String name, double length, double price, String unit, String usage, String type) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.price = length * price;
        this.unit = unit;
        this.usage = usage;
        this.type = type;
        this.category = "Tree";
    }

    public Material(String name, double length, double price, String unit, String usage, String type) {
        this.id = -1;
        this.name = name;
        this.length = length;
        this.price = length * price;
        this.unit = unit;
        this.usage = usage;
        this.type = type;
        this.category = "Tree";
    }
    
    public Material(int id, String name, double price, String unit, String usage, String type) {
        this.id = id;
        this.name = name;
        this.length = 0;
        this.price = price;
        this.unit = unit;
        this.usage = usage;
        this.type = type;
        this.category = "Options";
    }

    public Material(String name, double price, String unit, String usage, String type) {
        this.id = -1;
        this.name = name;
        this.length = 0;
        this.price = price;
        this.unit = unit;
        this.usage = usage;
        this.type = type;
        this.category = "Options";
    }

    public String getCategory() {
        return category;
    }

    public String getUnit() {
        return unit;
    }

    public double getLength() {
        return length;
    }

    public String getUsage() {
        return usage;
    }

    public String getType() {
        return type;
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

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", length=" + length +
                ", price=" + price +
                ", usage='" + usage + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
















    //    public enum usage {
//        //Flat roof
//        UnderStarFronBack,
//        UnderStarSide,
//        OverStarFronBack,
//        OverStarSide,
//        Door,
//        ShedGables,
//        ShedSide,
//        Straps,
//        ShedStraps,
//        Roof,
//        CustomRoof,
//        Foundation,
//        ShedCladding,
//        WaterStarSide,
//        WaterStartEnd,
//        RoffingBig,
//        FoffingSmall,
//
//        //Peak roof
//        Windshield,
//        StarSide,
//        ShedStarSide,
//        GablesCladding,
//        WindshieldsBoard,
//        WindshieldsGavel,
//
//
//        //Shed
//        Crossings,
//        RaftersRight,
//        RaftersLeft,
//        StarWaterBoard,
//        UniversalFittings,
//        Pole,
//        InnerCladding,
//        OuterCladding,
//        Shed,
//
//        //Shed with peak roof
//        RoofBattens,
//        TopLath,
//        Rafters,
//        BackStone,
//        RoofTiles,
//        Star
//    }
//
//    protected final int id;
//    protected final String name;
//    protected final double price;
//    protected final Enum<usage> usage;
//
//    public Material(int id, String name, double price, Enum<usage> usage) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.usage = usage;
//    }
//
//    public Material(String name, double price, Enum<usage> usage) {
//        this.id = -1;
//        this.name = name;
//        this.price = price;
//        this.usage = usage;
//    }
//
//    public Enum<Material.usage> getUsage() {
//        return usage;
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
//    public abstract String toString();
}
