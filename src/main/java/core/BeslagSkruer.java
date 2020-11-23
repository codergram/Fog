package core;

public class BeslagSkruer extends Materiel{

    public BeslagSkruer(int mat_id, String mat_navn, double mat_pris) {
        super(mat_id, mat_navn, mat_pris);
    }

    public BeslagSkruer(String mat_navn, double mat_pris) {
        super(mat_navn, mat_pris);
    }

    public BeslagSkruer withId (int mat_id) {
        return new BeslagSkruer(mat_id, this.mat_navn, this.mat_pris);
    }

    public int getBes_id() {
        return mat_id;
    }

    public String getBes_navn() {
        return mat_navn;
    }

    public double getBes_pris() {
        return mat_pris;
    }
}
