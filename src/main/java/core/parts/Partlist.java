package core.parts;

import java.util.ArrayList;

public class Partlist {

    private volatile ArrayList<Part_Træ> styklisteTræ;
    private volatile ArrayList<Part_BeslagSkruer> styklisteBeslagSkruer;

    public Partlist() {
        this.styklisteTræ = new ArrayList<>();
        this.styklisteBeslagSkruer = new ArrayList<>();
    }

    public Partlist(ArrayList<Part_Træ> styklisteTræ, ArrayList<Part_BeslagSkruer> styklisteBeslagSkruer) {
        this.styklisteTræ = styklisteTræ;
        this.styklisteBeslagSkruer = styklisteBeslagSkruer;
    }

    public ArrayList<Part_Træ> getStyklisteTræ() {
        return styklisteTræ;
    }

    public void setStyklisteTræ(ArrayList<Part_Træ> stykliste) {
        this.styklisteTræ = stykliste;
    }

    public ArrayList<Part_BeslagSkruer> getStyklisteBeslagSkruer() {
        return styklisteBeslagSkruer;
    }

    public void setStyklisteBeslagSkruer(ArrayList<Part_BeslagSkruer> styklisteBeslagSkruer) {
        this.styklisteBeslagSkruer = styklisteBeslagSkruer;
    }
}
