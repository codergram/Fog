package domain.partslist.exceptions;

import domain.carport.Carport;
import domain.material.materials.Material;
import domain.partslist.Part;

import java.util.List;

public interface PartslistServices extends PartslistFactory {
    
    List<Part> addToPartslist(Carport carport, List<Material> allMaterialsFromDB, List<Part> localPartlist);
    
}
