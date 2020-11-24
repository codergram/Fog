package core;

import core.parts.Part_BeslagSkruer;
import core.parts.Part_Træ;
import core.parts.Partlist;

import java.util.ArrayList;

public class Order {

    private final int bes_id;
    private final String bes_tid;
    private final String bes_status;
    private final int bes_aktiv;
    private final double bes_subtotal;
    private final double bes_moms;
    private final double bes_total;
    private final Partlist partlist;
    private final Kunde kunde;
    private final Carport carport;
    private final Skur skur;

    public Order(int bes_id, String bes_tid, String bes_status, double bes_moms, Partlist partlist, Kunde kunde, Carport carport, Skur skur) {
        this.bes_id = bes_id;
        this.bes_tid = bes_tid;
        this.bes_status = bes_status;
        this.bes_aktiv = 1;
        this.bes_subtotal = calculate_subtotal(partlist.getStyklisteTræ(), partlist.getStyklisteBeslagSkruer());
        this.bes_moms = bes_moms;
        this.bes_total = calculate_total(partlist.getStyklisteTræ(), partlist.getStyklisteBeslagSkruer(), bes_moms);
        this.partlist = partlist;
        this.kunde = kunde;
        this.carport = carport;
        this.skur = skur;
    }

    public Order(String bes_tid, String bes_status, double bes_moms, Partlist partlist, Kunde kunde, Carport carport, Skur skur) {
        this.bes_id = -1;
        this.bes_tid = bes_tid;
        this.bes_status = bes_status;
        this.bes_aktiv = 1;
        this.bes_subtotal = calculate_subtotal(partlist.getStyklisteTræ(), partlist.getStyklisteBeslagSkruer());
        this.bes_moms = bes_moms;
        this.bes_total = calculate_total(partlist.getStyklisteTræ(), partlist.getStyklisteBeslagSkruer(), bes_moms);
        this.partlist = partlist;
        this.kunde = kunde;
        this.carport = carport;
        this.skur = skur;
    }

    public Order withId (int bes_id) {
        return new Order(bes_id, this.bes_tid, this.bes_status, this.bes_moms, this.partlist, this.kunde, this.carport, this.skur);
    }

    public int getBes_id() {
        return bes_id;
    }

    public String getBes_tid() {
        return bes_tid;
    }

    public String getBes_status() {
        return bes_status;
    }

    public double getBes_subtotal() {
        return bes_subtotal;
    }

    public double getBes_moms() {
        return bes_moms;
    }

    public double getBes_total() {
        return bes_total;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public Carport getCarport() {
        return carport;
    }

    public Skur getSkur() {
        return skur;
    }

    private double calculate_subtotal(ArrayList<Part_Træ> styklisteTræ, ArrayList<Part_BeslagSkruer> styklisteBeslagSkruers) {

        double bes_subtotal = 0;

        for(Part_Træ currentElement: styklisteTræ){
            bes_subtotal = bes_subtotal + currentElement.getSubTotal();
        }

        for(Part_BeslagSkruer currentElement: styklisteBeslagSkruers){
            bes_subtotal = bes_subtotal + currentElement.getSubTotal();
        }

        return bes_subtotal;
    }

    private double calculate_total(ArrayList<Part_Træ> styklisteTræ, ArrayList<Part_BeslagSkruer> styklisteBeslagSkruers, double bes_moms) {
        return calculate_subtotal(styklisteTræ, styklisteBeslagSkruers) * (1 + (bes_moms / 100));
    }

}
