package core;

public class Carport {

    private final int carport_id;
    private final String carport_type;
    private final int længde;
    private final int højde;


    public Carport(int carport_id, String carport_type, int længde, int højde) {
        this.carport_id = carport_id;
        this.carport_type = carport_type;
        this.længde = længde;
        this.højde = højde;
    }

    public Carport(String carport_type, int længde, int højde) {
        this.carport_id = -1;
        this.carport_type = carport_type;
        this.længde = længde;
        this.højde = højde;
    }

    public Carport withId (int carport_id) {
        return new Carport(carport_id, this.carport_type, this.længde, this.højde);
    }

    public int getCarport_id() {
        return carport_id;
    }

    public String getCarport_type() {
        return carport_type;
    }

    public int getLængde() {
        return længde;
    }

    public int getHøjde() {
        return højde;
    }
}
