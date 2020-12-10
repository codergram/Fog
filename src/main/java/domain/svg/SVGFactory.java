/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.svg;

import domain.carport.Carport;

public interface SVGFactory {

  SVGSide createSVGSideCarport(Carport carport, boolean isCustomer);

  SVGTop createSVGTopCarport(Carport carport, boolean isCustomer);
}
