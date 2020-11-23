package core;

public class Styk {

    protected final int styk_id;
    protected final String styk_bes;
    protected final double styk_antal;

    public Styk(int styk_id, String styk_bes, double styk_antal) {
        this.styk_id = styk_id;
        this.styk_bes = styk_bes;
        this.styk_antal = styk_antal;
    }

    public Styk(String styk_bes, double styk_antal) {
        this.styk_id = -1;
        this.styk_bes = styk_bes;
        this.styk_antal = styk_antal;
    }
}
