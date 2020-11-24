package core.parts;

import core.materials.Options;

abstract public class Part {

    protected final int styk_id;
    protected final String styk_bes;
    protected final double styk_antal;

    public Part(int styk_id, String styk_bes, double styk_antal) {
        this.styk_id = styk_id;
        this.styk_bes = styk_bes;
        this.styk_antal = styk_antal;
    }

    public Part(String styk_bes, double styk_antal) {
        this.styk_id = -1;
        this.styk_bes = styk_bes;
        this.styk_antal = styk_antal;
    }
    
    abstract double calculateSubTotal(int antal);
}
