package core;

public class BeslagSkruer {

    private final int bes_id;
    private final String bes_navn;
    private final double bes_pris;


    public BeslagSkruer(int bes_id, String bes_navn, double bes_pris) {
        this.bes_id = bes_id;
        this.bes_navn = bes_navn;
        this.bes_pris = bes_pris;
    }

    public BeslagSkruer(String bes_navn, double bes_pris) {
        this.bes_id = -1;
        this.bes_navn = bes_navn;
        this.bes_pris = bes_pris;
    }

    public BeslagSkruer withId (int bes_id) {
        return new BeslagSkruer(bes_id, this.bes_navn, this.bes_pris);
    }

    public int getBes_id() {
        return bes_id;
    }

    public String getBes_navn() {
        return bes_navn;
    }

    public double getBes_pris() {
        return bes_pris;
    }
}
