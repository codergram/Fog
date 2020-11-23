package core;

import java.util.ArrayList;

public class Bestilling {

    private final int bes_id;
    private final String bes_tid;
    private final String bes_status;
    private final int bes_aktiv;
    private final double bes_subtotal;
    private final double bes_moms;
    private final double bes_total;
    private final Stykliste stykliste;
    private final Kunde kunde;
    private final Carport carport;
    private final Skur skur;

    public Bestilling(int bes_id, String bes_tid, String bes_status, double bes_moms, Stykliste stykliste, Kunde kunde, Carport carport, Skur skur) {
        this.bes_id = bes_id;
        this.bes_tid = bes_tid;
        this.bes_status = bes_status;
        this.bes_aktiv = 1;
        this.bes_subtotal = calculate_subtotal(stykliste.getStyklisteTræ(), stykliste.getStyklisteBeslagSkruer());
        this.bes_moms = bes_moms;
        this.bes_total = calculate_total(stykliste.getStyklisteTræ(), stykliste.getStyklisteBeslagSkruer(), bes_moms);
        this.stykliste = stykliste;
        this.kunde = kunde;
        this.carport = carport;
        this.skur = skur;
    }

    public Bestilling(String bes_tid, String bes_status, double bes_moms, Stykliste stykliste, Kunde kunde, Carport carport, Skur skur) {
        this.bes_id = -1;
        this.bes_tid = bes_tid;
        this.bes_status = bes_status;
        this.bes_aktiv = 1;
        this.bes_subtotal = calculate_subtotal(stykliste.getStyklisteTræ(), stykliste.getStyklisteBeslagSkruer());
        this.bes_moms = bes_moms;
        this.bes_total = calculate_total(stykliste.getStyklisteTræ(), stykliste.getStyklisteBeslagSkruer(), bes_moms);
        this.stykliste = stykliste;
        this.kunde = kunde;
        this.carport = carport;
        this.skur = skur;
    }

    public Bestilling withId (int bes_id) {
        return new Bestilling(bes_id, this.bes_tid, this.bes_status, this.bes_moms, this.stykliste, this.kunde, this.carport, this.skur);
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

    private double calculate_subtotal(ArrayList<Styk_Træ> styklisteTræ, ArrayList<Styk_BeslagSkruer> styklisteBeslagSkruers) {

        double bes_subtotal = 0;

        for(Styk_Træ currentElement: styklisteTræ){
            bes_subtotal = bes_subtotal + currentElement.getStyk_subtotal();
        }

        for(Styk_BeslagSkruer currentElement: styklisteBeslagSkruers){
            bes_subtotal = bes_subtotal + currentElement.getStyk_subtotal();
        }

        return bes_subtotal;
    }

    private double calculate_total(ArrayList<Styk_Træ> styklisteTræ, ArrayList<Styk_BeslagSkruer> styklisteBeslagSkruers, double bes_moms) {
        return calculate_subtotal(styklisteTræ, styklisteBeslagSkruers) * (1 + (bes_moms / 100));
    }

}
