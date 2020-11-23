package core;

import java.util.ArrayList;

public class Stykliste {

    private volatile ArrayList<Styk_Træ> styklisteTræ;
    private volatile ArrayList<Styk_BeslagSkruer> styklisteBeslagSkruer;

    public Stykliste() {
        this.styklisteTræ = new ArrayList<>();
        this.styklisteBeslagSkruer = new ArrayList<>();
    }

    public Stykliste(ArrayList<Styk_Træ> styklisteTræ, ArrayList<Styk_BeslagSkruer> styklisteBeslagSkruer) {
        this.styklisteTræ = styklisteTræ;
        this.styklisteBeslagSkruer = styklisteBeslagSkruer;
    }

    public ArrayList<Styk_Træ> getStyklisteTræ() {
        return styklisteTræ;
    }

    public void setStyklisteTræ(ArrayList<Styk_Træ> stykliste) {
        this.styklisteTræ = stykliste;
    }

    public ArrayList<Styk_BeslagSkruer> getStyklisteBeslagSkruer() {
        return styklisteBeslagSkruer;
    }

    public void setStyklisteBeslagSkruer(ArrayList<Styk_BeslagSkruer> styklisteBeslagSkruer) {
        this.styklisteBeslagSkruer = styklisteBeslagSkruer;
    }
}
