//package domain.material.materials;
//
//public class Tree extends Material {
//
//    public enum type {
//        Poles,
//        Boards,
//        Laths,
//        Rules,
//        Rafts,
//        Roofing
//    }
//
//    private final double length;
//    private final Enum<type> type;
//
//    public Tree(int id, String name, double length, double price, Enum<usage> usage, Enum<type> type) {
//        super(id, name, length * price, usage);
//        this.length = length;
//        this.type = type;
//    }
//    public Tree(String name, double length, double price, Enum<usage> usage, Enum<type> type) {
//        super(name, length * price, usage);
//        this.length = length;
//        this.type = type;
//
//    }
//
//    public double getLength() {
//        return length;
//    }
//
//    public Enum<Tree.type> getType() {
//        return type;
//    }
//
//    @Override
//    public String toString() {
//        return "Tree{" +
//                "length=" + length +
//                ", id=" + id +
//                ", navn='" + name + '\'' +
//                ", pris=" + price +
//                '}';
//    }
//}
