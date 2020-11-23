package core;

public class Styk_BeslagSkruer extends Styk{

    private final Materiel_BeslagSkruer materielBeslagSkruer;
    private final double styk_subtotal;

    public Styk_BeslagSkruer(int styk_id, Materiel_BeslagSkruer materielBeslagSkruer, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.materielBeslagSkruer = materielBeslagSkruer;
        this.styk_subtotal = calculate_subtotal(materielBeslagSkruer, styk_antal);
    }

    public Styk_BeslagSkruer(Materiel_BeslagSkruer materielBeslagSkruer, String styk_bes, double styk_antal) {
        super(styk_bes, styk_antal);
        this.materielBeslagSkruer = materielBeslagSkruer;
        this.styk_subtotal = calculate_subtotal(materielBeslagSkruer, styk_antal);
    }

    public Styk_BeslagSkruer withId (int styk_id) {
        return new Styk_BeslagSkruer(styk_id, this.materielBeslagSkruer, this.styk_bes, this.styk_antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public Materiel_BeslagSkruer getBeslagSkruer() {
        return materielBeslagSkruer;
    }

    public double getAntal() {
        return styk_antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(Materiel_BeslagSkruer materielBeslagSkruer, double antal){

        return materielBeslagSkruer.getBes_pris() * antal;

    }
}
