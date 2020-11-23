package core;

public class Materiel {

    protected final int mat_id;
    protected final String mat_navn;
    protected final double mat_pris;

    public Materiel(int mat_id, String mat_navn, double mat_pris) {
        this.mat_id = mat_id;
        this.mat_navn = mat_navn;
        this.mat_pris = mat_pris;
    }

    public Materiel(String mat_navn, double mat_pris) {
        this.mat_id = -1;
        this.mat_navn = mat_navn;
        this.mat_pris = mat_pris;
    }
}
