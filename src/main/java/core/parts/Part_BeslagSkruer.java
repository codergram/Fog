package core.parts;

import core.materials.Options;

public class Part_BeslagSkruer extends Part {

    private final Options materielBeslagSkruer;
    private double subTotal;

    public Part_BeslagSkruer(int styk_id, Options materielBeslagSkruer, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.materielBeslagSkruer = materielBeslagSkruer;
    }

    public Part_BeslagSkruer(Options materielBeslagSkruer, String styk_bes, double styk_antal) {
        super(styk_bes, styk_antal);
        this.materielBeslagSkruer = materielBeslagSkruer;
    }

    public Part_BeslagSkruer withId (int styk_id) {
        return new Part_BeslagSkruer(styk_id, this.materielBeslagSkruer, this.styk_bes, this.styk_antal);
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
    
    public double getSubTotal() {
        return subTotal;
    }
    
    @Override
    double calculateSubTotal(int antal) {
        return this.materielBeslagSkruer.getPrice() * antal;
    }
}
