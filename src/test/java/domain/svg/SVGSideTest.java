/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.svg;

import domain.carport.Carport;
import domain.carport.shed.Shed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SVGSideTest {

  @Test
  void testSVGSide() {
    double length = 780;
    double width = 600;
    double shedLength = 210;
    double shedWidth = width - 75.0;
    Shed shed = new Shed(shedLength, shedWidth);
    Carport carport = new Carport(780, 600, Carport.Roof.Flat, shed);

    String expectedResult =
        "<svg version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\"\n"
            + "     height=\"100%\"  width=\"100%\" viewBox=\"0 0 930.0 330.0\"\n"
            + "     preserveAspectRatio=\"xMinYMin\">";

    SVGSide svgSide = new SVGSide(carport, false);

    assertTrue(svgSide.getSvgCode().startsWith(expectedResult));
  }
}
