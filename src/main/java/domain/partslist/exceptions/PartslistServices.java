package domain.partslist.exceptions;

import domain.carport.Carport;
import domain.partslist.Part;

import java.util.ArrayList;
import java.util.List;

public interface PartslistServices {

    ArrayList<Part> addToPartslist(Carport carport, List<Part> materialList);

}
