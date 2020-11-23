package core;

public class Stykliste_BeslagSkruer {

    private final int styk_id;
    private final BeslagSkruer beslagSkruer;
    private final String styk_bes;
    private final double antal;
    private final double styk_subtotal;

    public Stykliste_BeslagSkruer(int styk_id, BeslagSkruer beslagSkruer, String styk_bes, double antal) {
        this.styk_id = styk_id;
        this.beslagSkruer = beslagSkruer;
        this.styk_bes = styk_bes;
        this.antal = antal;
        this.styk_subtotal = calculate_subtotal(beslagSkruer, antal);
    }

    public Stykliste_BeslagSkruer(BeslagSkruer beslagSkruer, String styk_bes, double antal) {
        this.styk_id = -1;
        this.beslagSkruer = beslagSkruer;
        this.styk_bes = styk_bes;
        this.antal = antal;
        this.styk_subtotal = calculate_subtotal(beslagSkruer, antal);
    }

    public Stykliste_BeslagSkruer withId (int styk_id) {
        return new Stykliste_BeslagSkruer(styk_id, this.beslagSkruer, this.styk_bes, this.antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public BeslagSkruer getBeslagSkruer() {
        return beslagSkruer;
    }

    public double getAntal() {
        return antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(BeslagSkruer beslagSkruer, double antal){

        return beslagSkruer.getBes_pris() * antal;

    }
}
