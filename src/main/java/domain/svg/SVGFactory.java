package domain.svg;

import domain.carport.Carport;

public interface SVGFactory {
    
    SVGSide createSVGSideCarport(Carport carport, boolean isCustomer);
    
    SVGTop createSVGTopCarport(Carport carport, boolean isCustomer);
    
}
