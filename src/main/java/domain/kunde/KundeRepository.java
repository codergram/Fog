package domain.kunde;

import core.Kunde;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface KundeRepository {

    ArrayList<Kunde> getAllKunderFromDB() throws DBexception;

    Kunde getKundeById(int kunde_id) throws DBexception;

}
