package infrastructure;

import domain.carport.Carport;
import domain.svg.SVGFactory;
import domain.svg.SVGSide;
import domain.svg.SVGTop;

public class LocalSVG implements SVGFactory {
    @Override
    public SVGSide createSVGSideCarport(Carport carport, boolean isCustomer) {
        return new SVGSide(carport, isCustomer);
    }

    @Override
    public SVGTop createSVGTopCarport(Carport carport, boolean isCustomer) {
        return new SVGTop(carport, isCustomer);
    }
}
