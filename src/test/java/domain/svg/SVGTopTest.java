package domain.svg;

import domain.carport.Carport;
import domain.carport.shed.Shed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SVGTopTest {

    @Test
    void testSVGTop(){
        double length = 780;
        double width = 600;
        double shedLength = 210;
        double shedWidth =  width - 75.0;
        Shed shed = new Shed(shedLength, shedWidth);
        Carport carport = new Carport(780, 600, Carport.Roof.Flat, shed);

        String expectedResult = "<svg version=\"1.1\"\n" +
                "     xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "     xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                "     height=\"100%\"  width=\"100%\" viewBox=\"0 0 880.0 700.0\"\n" +
                "     preserveAspectRatio=\"xMinYMin\">";

        SVGTop svgTop = new SVGTop(carport, false);

//        System.out.println(svgTop.getSvgTop());

        assertTrue(svgTop.getSvgTop().startsWith(expectedResult));
    }

}