package core;

public class Stykliste_Materiel {

    private final int styk_id;
    private final Materiel materiel;
    private final double antal;
    private final double styk_subtotal;

    public Stykliste_Materiel(int styk_id, Materiel materiel, double antal) {
        this.styk_id = styk_id;
        this.materiel = materiel;
        this.antal = antal;
        this.styk_subtotal = materiel.getMat_pris() * antal;
    }

    public Stykliste_Materiel(Materiel materiel, double antal) {
        this.styk_id = -1;
        this.materiel = materiel;
        this.antal = antal;
        this.styk_subtotal = materiel.getMat_pris() * antal;
    }

    public Stykliste_Materiel withId (int styk_id) {
        return new Stykliste_Materiel(styk_id, this.materiel, this.antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public double getAntal() {
        return antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }
}
