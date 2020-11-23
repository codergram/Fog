package core;

public class Styk_Træ extends Styk{

    private final Træ træ;
    private final double styk_subtotal;

    public Styk_Træ(int styk_id, Træ træ, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.træ = træ;
        this.styk_subtotal = calculate_subtotal(træ, styk_antal);
    }

    public Styk_Træ(Træ træ, String styk_bes, double styk_antal) {
        super(styk_bes, styk_antal);
        this.træ = træ;
        this.styk_subtotal = calculate_subtotal(træ, styk_antal);
    }

    public Styk_Træ withId (int styk_id) {
        return new Styk_Træ(styk_id, this.træ, this.styk_bes, this.styk_antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public Træ getTræ() {
        return træ;
    }

    public double getAntal() {
        return styk_antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(Træ træ, double antal){
        return ((træ.getTræ_mål() / 100) * træ.getTræ_pris()) * antal;
    }
}
