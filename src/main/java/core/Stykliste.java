package core;

import java.util.ArrayList;

public class Stykliste {

    private volatile ArrayList<Stykliste_Materiel> stykliste;

    public Stykliste() {
        this.stykliste = new ArrayList<>();
    }

    public Stykliste(ArrayList<Stykliste_Materiel> stykliste) {
        this.stykliste = stykliste;
    }

    public ArrayList<Stykliste_Materiel> getStykliste() {
        return stykliste;
    }

    public void setStykliste(ArrayList<Stykliste_Materiel> stykliste) {
        this.stykliste = stykliste;
    }
}
