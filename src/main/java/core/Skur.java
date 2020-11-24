package core;

public class Skur {
    
    private final int skur_id;
    private final int længde;
    private final int højde;
    
    public Skur(int skur_id, int længde, int højde) {
        this.skur_id = skur_id;
        this.længde = længde;
        this.højde = højde;
    }
    
    public Skur(int længde, int højde) {
        this.skur_id = -1;
        this.længde = længde;
        this.højde = højde;
    }
    
    
    public Skur withId (int skur_id) {
        return new Skur(skur_id, this.længde, this.højde);
    }
    
    public int getSkur_id() {
        return skur_id;
    }
    
    public int getLængde() {
        return længde;
    }
    
    public int getHøjde() {
        return højde;
    }
}