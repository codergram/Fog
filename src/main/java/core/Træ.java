package core;

public class Træ {

    private final int træ_id;
    private final String træ_navn;
    private final int træ_mål;
    private final double træ_pris;


    public Træ(int træ_id, String træ_navn, int træ_mål, double træ_pris) {
        this.træ_id = træ_id;
        this.træ_navn = træ_navn;
        this.træ_mål = træ_mål;
        this.træ_pris = træ_pris;
    }

    public Træ(String træ_navn, int træ_mål, double træ_pris) {
        this.træ_id = -1;
        this.træ_navn = træ_navn;
        this.træ_mål = træ_mål;
        this.træ_pris = træ_pris;
    }

    public Træ withId (int træ_id) {
        return new Træ(træ_id, this.træ_navn, this.træ_mål, this.træ_pris);
    }

    public int getTræ_id() {
        return træ_id;
    }

    public String getTræ_navn() {
        return træ_navn;
    }

    public double getTræ_pris() {
        return træ_pris;
    }

    public int getTræ_mål() {
        return træ_mål;
    }
}
