package core;

public class Styk_BeslagSkruer extends Styk{

    private final BeslagSkruer beslagSkruer;
    private final double styk_subtotal;

    public Styk_BeslagSkruer(int styk_id, BeslagSkruer beslagSkruer, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.beslagSkruer = beslagSkruer;
        this.styk_subtotal = calculate_subtotal(beslagSkruer, styk_antal);
    }

    public Styk_BeslagSkruer(BeslagSkruer beslagSkruer, String styk_bes, double styk_antal) {
        super(styk_bes, styk_antal);
        this.beslagSkruer = beslagSkruer;
        this.styk_subtotal = calculate_subtotal(beslagSkruer, styk_antal);
    }

    public Styk_BeslagSkruer withId (int styk_id) {
        return new Styk_BeslagSkruer(styk_id, this.beslagSkruer, this.styk_bes, this.styk_antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public BeslagSkruer getBeslagSkruer() {
        return beslagSkruer;
    }

    public double getAntal() {
        return styk_antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(BeslagSkruer beslagSkruer, double antal){

        return beslagSkruer.getBes_pris() * antal;

    }
}
