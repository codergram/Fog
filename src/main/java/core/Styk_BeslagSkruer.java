package core;

import core.materialer.Options;

public class Styk_BeslagSkruer extends Styk{

    private final Options materielBeslagSkruer;
    private final double styk_subtotal;

    public Styk_BeslagSkruer(int styk_id, Options materielBeslagSkruer, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.materielBeslagSkruer = materielBeslagSkruer;
        this.styk_subtotal = calculate_subtotal(materielBeslagSkruer, styk_antal);
    }

    public Styk_BeslagSkruer(Options materielBeslagSkruer, String styk_bes, double styk_antal) {
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

    public Options getBeslagSkruer() {
        return materielBeslagSkruer;
    }

    public double getAntal() {
        return styk_antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(Options materielBeslagSkruer, double antal){

        return materielBeslagSkruer.getPrice() * antal;

    }
}
