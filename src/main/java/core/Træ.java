package core;

public class Træ extends Materiel{

    private final int træ_mål;

    public Træ(int mat_id, String mat_navn, int træ_mål, double mat_pris) {
        super(mat_id, mat_navn, mat_pris);
        this.træ_mål = træ_mål;
    }

    public Træ(String mat_navn, int træ_mål, double mat_pris) {
        super(mat_navn, mat_pris);
        this.træ_mål = træ_mål;
    }

    public Træ withId (int mat_id) {
        return new Træ(mat_id, this.mat_navn, this.træ_mål, this.mat_pris);
    }

    public int getTræ_id() {
        return mat_id;
    }

    public String getTræ_navn() {
        return mat_navn;
    }

    public double getTræ_pris() {
        return mat_pris;
    }

    public int getTræ_mål() {
        return træ_mål;
    }
}
