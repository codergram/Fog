package core;

import java.util.ArrayList;

public class Stykliste {

    private volatile ArrayList<Stykliste_Træ> styklisteTræ;
    private volatile ArrayList<Stykliste_BeslagSkruer> styklisteBeslagSkruer;

    public Stykliste() {
        this.styklisteTræ = new ArrayList<>();
        this.styklisteBeslagSkruer = new ArrayList<>();
    }

    public Stykliste(ArrayList<Stykliste_Træ> styklisteTræ, ArrayList<Stykliste_BeslagSkruer> styklisteBeslagSkruer) {
        this.styklisteTræ = styklisteTræ;
        this.styklisteBeslagSkruer = styklisteBeslagSkruer;
    }

    public ArrayList<Stykliste_Træ> getStyklisteTræ() {
        return styklisteTræ;
    }

    public void setStyklisteTræ(ArrayList<Stykliste_Træ> stykliste) {
        this.styklisteTræ = stykliste;
    }

    public ArrayList<Stykliste_BeslagSkruer> getStyklisteBeslagSkruer() {
        return styklisteBeslagSkruer;
    }

    public void setStyklisteBeslagSkruer(ArrayList<Stykliste_BeslagSkruer> styklisteBeslagSkruer) {
        this.styklisteBeslagSkruer = styklisteBeslagSkruer;
    }
}
