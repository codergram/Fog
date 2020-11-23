package core;

import core.materialer.Tree;

public class Styk_Træ extends Styk{

    private final Tree materielTræ;
    private final double styk_subtotal;

    public Styk_Træ(int styk_id, Tree materielTræ, String styk_bes, double styk_antal) {
        super(styk_id, styk_bes, styk_antal);
        this.materielTræ = materielTræ;
        this.styk_subtotal = calculate_subtotal(materielTræ, styk_antal);
    }

    public Styk_Træ(Tree materielTræ, String styk_bes, double styk_antal) {
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

    public Tree getTræ() {
        return materielTræ;
    }

    public double getAntal() {
        return styk_antal;
    }

    public double getStyk_subtotal() {
        return styk_subtotal;
    }

    private double calculate_subtotal(Tree tree, double antal){
        return ((tree.getLength() / 100) * tree.getPrice()) * antal;
    }
}
