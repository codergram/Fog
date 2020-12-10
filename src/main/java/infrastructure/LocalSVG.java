/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

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
