package core;

public class Bestilling {

    private final int bes_id;
    private final String bes_tid;
    private final String bes_status;
    private final int bes_aktiv;
    private final double bes_subtotal;
    private final double bes_moms;
    private final double bes_total;
    private final Kunde kunde;
    private final Carport carport;
    private final Skur skur;

    public Bestilling(int bes_id, String bes_tid, String bes_status, double bes_subtotal, double bes_moms, double bes_total, Kunde kunde, Carport carport, Skur skur) {
        this.bes_id = bes_id;
        this.bes_tid = bes_tid;
        this.bes_status = bes_status;
        this.bes_aktiv = 1;
        this.bes_subtotal = bes_subtotal;
        this.bes_moms = bes_moms;
        this.bes_total = bes_total;
        this.kunde = kunde;
        this.carport = carport;
        this.skur = skur;
    }

    public Bestilling(String bes_tid, String bes_status, double bes_subtotal, double bes_moms, double bes_total, Kunde kunde, Carport carport, Skur skur) {
        this.bes_id = -1;
        this.bes_tid = bes_tid;
        this.bes_status = bes_status;
        this.bes_aktiv = 1;
        this.bes_subtotal = bes_subtotal;
        this.bes_moms = bes_moms;
        this.bes_total = bes_total;
        this.kunde = kunde;
        this.carport = carport;
        this.skur = skur;
    }

    public Bestilling withId (int bes_id) {
        return new Bestilling(bes_id, this.bes_tid, this.bes_status, this.bes_subtotal, this.bes_moms, this.bes_total, this.kunde, this.carport, this.skur);
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
}
