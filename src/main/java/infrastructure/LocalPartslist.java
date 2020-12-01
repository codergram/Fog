package infrastructure;

import domain.carport.Carport;
import domain.partslist.Part;
import domain.partslist.exceptions.PartslistServices;

import java.util.ArrayList;
import java.util.List;

public class LocalPartslist implements PartslistServices {
    @Override
    public ArrayList<Part> addToPartslist(Carport carport, List<Part> materialList) {
        return null;
    }
}
