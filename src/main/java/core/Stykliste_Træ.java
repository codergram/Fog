package core;

public class Stykliste_Træ {

    private final int styk_id;
    private final Træ træ;
    private final String styk_bes;
    private final double antal;
    private final double styk_subtotal;

    public Stykliste_Træ(int styk_id, Træ træ, String styk_bes, double antal) {
        this.styk_id = styk_id;
        this.træ = træ;
        this.styk_bes = styk_bes;
        this.antal = antal;
        this.styk_subtotal = calculate_subtotal(træ, antal);
    }

    public Stykliste_Træ(Træ træ, String styk_bes, double antal) {
        this.styk_id = -1;
        this.træ = træ;
        this.styk_bes = styk_bes;
        this.antal = antal;
        this.styk_subtotal = calculate_subtotal(træ, antal);
    }

    public Stykliste_Træ withId (int styk_id) {
        return new Stykliste_Træ(styk_id, this.træ, this.styk_bes, this.antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public Træ getTræ() {
        return træ;
    }

    public double getAntal() {
        return antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(Træ træ, double antal){
        return ((træ.getTræ_mål() / 100) * træ.getTræ_pris()) * antal;
    }
}
