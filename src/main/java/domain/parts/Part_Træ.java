package domain.parts;

import domain.materiel.materials.Tree;

public class Part_Træ extends Part {

    private final Tree materielTræ;
    private double subTotal;

    public Part_Træ(int styk_id, Tree materielTræ, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.materielTræ = materielTræ;
    }

    public Part_Træ(Tree materielTræ, String styk_bes, double styk_antal) {
        super(styk_bes, styk_antal);
        this.materielTræ = materielTræ;
    }

    public Part_Træ withId (int styk_id) {
        return new Part_Træ(styk_id, this.materielTræ, this.styk_bes, this.styk_antal);
    }

    public int getStyk_id() {
        return styk_id;
    }

    public Tree getTræ() {
        return materielTræ;
    }

    public double getAntal() {
        return styk_antal;
    }
    
    public double getSubTotal() {
        return subTotal;
    }
    
    @Override
    double calculateSubTotal(int antal) {
        return ((this.materielTræ.getLength() / 100) * this.materielTræ.getPrice()) * antal;
    }
}
