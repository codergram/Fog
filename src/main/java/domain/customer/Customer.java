package domain.customer;

public class Customer {

    private final int kunde_id;
    private final String navn;
    private final String adresse;
    private final int post_nummer;
    private final String by;
    private final int tlf_nr;
    private final String email;

    public Customer(int kunde_id, String navn, String adresse, int post_nummer, String by, int tlf_nr, String email) {
        this.kunde_id = kunde_id;
        this.navn = navn;
        this.adresse = adresse;
        this.post_nummer = post_nummer;
        this.by = by;
        this.tlf_nr = tlf_nr;
        this.email = email;
    }

    public Customer(String navn, String adresse, int post_nummer, String by, int tlf_nr, String email) {
        this.kunde_id = -1;
        this.navn = navn;
        this.adresse = adresse;
        this.post_nummer = post_nummer;
        this.by = by;
        this.tlf_nr = tlf_nr;
        this.email = email;
    }

    //Is used when we get orders from the DB where we don't need all the users info's
    public Customer(String navn, int tlf_nr, String email) {
        this.kunde_id = -1;
        this.navn = navn;
        this.adresse = "";
        this.post_nummer = 0;
        this.by = "";
        this.tlf_nr = tlf_nr;
        this.email = email;
    }

    public Customer withId (int kunde_id) {
        return new Customer(kunde_id, this.navn, this.adresse, this.post_nummer, this.by, this.tlf_nr, this.email);
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getPost_nummer() {
        return post_nummer;
    }

    public String getBy() {
        return by;
    }

    public int getTlf_nr() {
        return tlf_nr;
    }

    public String getEmail() {
        return email;
    }
}
