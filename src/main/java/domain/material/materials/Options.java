//package domain.material.materials;
//
//public class Options extends Material {
//
//    public enum Type{
//        BottomScrews,
//        PerforatedTape,
//        Universal,
//        Screw,
//        FittingScrews,
//        BoardBolt,
//        SquareDiscs,
//        BarnDoorHandle,
//        Hinge,
//        AngleBracket,
//        Dobbelt,
//        BackStone,
//        TopLayer,
//        BackstoneFittings,
//        RoofBinders,
//    }
//
//    private final Enum<Tree.type> type;
//
//    public Options(int id, String name, double price, Enum<usage> usage, Enum<Tree.type> type) {
//        super(id, name, price, usage);
//        this.type = type;
//    }
//
//    public Options(String name, double price, Enum<usage> usage, Enum<Tree.type> type) {
//        super(name, price, usage);
//        this.type = type;
//    }
//
//    public Enum<Tree.type> getType() {
//        return type;
//    }
//
//    @Override
//    public String toString() {
//        return "Options{" +
//                "id=" + id +
//                ", navn='" + name + '\'' +
//                ", pris=" + price +
//                '}';
//    }
//}
