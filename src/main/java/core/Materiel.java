package core;

public class Materiel {

    private final int mat_id;
    private final String mat_navn;
    private final double mat_pris;


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

    public Materiel withId (int mat_id) {
        return new Materiel(mat_id, this.mat_navn, this.mat_pris);
    }

    public int getMat_id() {
        return mat_id;
    }

    public String getMat_navn() {
        return mat_navn;
    }

    public double getMat_pris() {
        return mat_pris;
    }
}
