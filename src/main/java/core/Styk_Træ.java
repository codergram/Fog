package core;

public class Styk_Træ extends Styk{

    private final Materiel_Træ materielTræ;
    private final double styk_subtotal;

    public Styk_Træ(int styk_id, Materiel_Træ materielTræ, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.materielTræ = materielTræ;
        this.styk_subtotal = calculate_subtotal(materielTræ, styk_antal);
    }

    public Styk_Træ(Materiel_Træ materielTræ, String styk_bes, double styk_antal) {
        super(styk_bes, styk_antal);
        this.materielTræ = materielTræ;
        this.styk_subtotal = calculate_subtotal(materielTræ, styk_antal);
    }

    public Styk_Træ withId (int styk_id) {
        return new Styk_Træ(styk_id, this.materielTræ, this.styk_bes, this.styk_antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public Materiel_Træ getTræ() {
        return materielTræ;
    }

    public double getAntal() {
        return styk_antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(Materiel_Træ materielTræ, double antal){
        return ((materielTræ.getTræ_mål() / 100) * materielTræ.getTræ_pris()) * antal;
    }
}
