package domain.svg;


import domain.carport.Carport;

public class SVGTop {

    private double length;
    private double width;
    private String roof;
    private boolean withShed;
    private double shedLength;
    private double shedWidth;
    private StringBuilder sb;
    private String SVGside;
    private boolean isCustomer;

    public SVGTop(Carport carport, boolean isCustomer) {
        this.length = carport.getLength();
        this.width = carport.getWidth();
        this.withShed = carport.hasShed();
        this.shedLength = 0.0;
        this.shedWidth = 0.0;
        if(withShed) {
            this.shedLength = carport.getShed().getLength();
            this.shedWidth = carport.getShed().getWidth();
        }
        this.roof = carport.getRoofType();
        this.sb = new StringBuilder();
        this.SVGside = null;
        this.isCustomer = isCustomer;
    }

    public String getSvgTop() {
        sb.append(createHeader());
        if(!isCustomer){
            sb = sb.append(lodretMal());
            sb = sb.append(vandretMal());
        }
        sb = sb.append(carportHeader());
        sb = sb.append(carportRammeogSider());

        sb = sb.append(carportSpaer());
        if(!isCustomer){
            sb = sb.append(carportSpaerMal());
        }

        sb = sb.append(carportKryds());
        sb = sb.append(carportBagForkant());
        sb = sb.append(carportSpear());
        sb = sb.append(carportStolper());
        sb = sb.append(carporFooter());
        if(withShed){
            sb = sb.append(skur());
        }
        sb = sb.append(footer());
        SVGside = sb.toString();
        return SVGside;
    }

    private String createHeader(){
        // først " og så ).append().append("
        // først " og så  + () + "
        String svgText;
        double headerWidth = width + 100;
        double headerLengt = length + 100;

        if(roof.equals("Peak")){
            svgText = "<svg version=\"1.1\"\n" +
                    "     xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "     xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                    "     height=\"" + headerWidth + "\"  width=\"" + headerLengt + "\" viewBox=\"0 0 " + headerLengt + " " + headerWidth + "\"\n" +
                    "     preserveAspectRatio=\"xMinYMin\">\n" +
                    "\n" +
                    "    <!--    Hvid ramme-->\n" +
                    "    <rect x=\"0\" y=\"0\" height=\"" + headerWidth + "\" width=\"" + headerLengt + "\"\n" +
                    "          style=\"stroke:#fff; fill: #fff\"/>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    <defs>\n" +
                    "        <marker\n" +
                    "                id=\"beginArrow\"\n" +
                    "                markerWidth=\"12\"\n" +
                    "                markerHeight=\"12\"\n" +
                    "                refX=\"0\"\n" +
                    "                refY=\"6\"\n" +
                    "                orient=\"auto\">\n" +
                    "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                    "        </marker>\n" +
                    "        <marker\n" +
                    "                id=\"endArrow\"\n" +
                    "                markerWidth=\"12\"\n" +
                    "                markerHeight=\"12\"\n" +
                    "                refX=\"12\"\n" +
                    "                refY=\"6\"\n" +
                    "                orient=\"auto\">\n" +
                    "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                    "        </marker>\n" +
                    "    </defs>";

        } else {
            svgText = "<svg version=\"1.1\"\n" +
                    "     xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "     xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                    "     height=\"" + headerWidth + "\"  width=\"" + headerLengt + "\" viewBox=\"0 0 " + headerLengt + " " + headerWidth + "\"\n" +
                    "     preserveAspectRatio=\"xMinYMin\">\n" +
                    "\n" +
                    "    <!--    Hvid ramme-->\n" +
                    "    <rect x=\"0\" y=\"0\" height=\"" + headerWidth + "\" width=\"" + headerLengt + "\"\n" +
                    "          style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "    \n" +
                    "    <defs>\n" +
                    "        <marker\n" +
                    "                id=\"beginArrow\"\n" +
                    "                markerWidth=\"12\"\n" +
                    "                markerHeight=\"12\"\n" +
                    "                refX=\"0\"\n" +
                    "                refY=\"6\"\n" +
                    "                orient=\"auto\">\n" +
                    "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
                    "        </marker>\n" +
                    "        <marker\n" +
                    "                id=\"endArrow\"\n" +
                    "                markerWidth=\"12\"\n" +
                    "                markerHeight=\"12\"\n" +
                    "                refX=\"12\"\n" +
                    "                refY=\"6\"\n" +
                    "                orient=\"auto\">\n" +
                    "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
                    "        </marker>\n" +
                    "    </defs>";
        }

        return svgText;
    }

    private String lodretMal(){
        String svgText;
        double fixedWidth = width;

        if(roof.equals("Peak")){
            svgText = "<line x1=\"20\"  y1=\"10\" x2=\"20\"   y2=\"" + (fixedWidth + 10) + "\"\n" +
                    "          style=\"stroke: #000000;\n" +
                    "\tmarker-start: url(#beginArrow);\n" +
                    "\tmarker-end: url(#endArrow);\"/>\n" +
                    "\n" +
                    "    <text style=\"text-anchor: middle\" transform=\"translate(14," + (fixedWidth/2 + 5) + ") rotate(-90)\">" + (fixedWidth) + " cm</text>\n" +
                    "\n" +
                    "\n" +
                    "    <line x1=\"45\"  y1=\"30\" x2=\"45\"   y2=\"" + (fixedWidth - 20) + "\"\n" +
                    "          style=\"stroke: #000000;\n" +
                    "\tmarker-start: url(#beginArrow);\n" +
                    "\tmarker-end: url(#endArrow);\"/>\n" +
                    "\n" +
                    "    <text style=\"text-anchor: middle\" transform=\"translate(40," + (fixedWidth/2 + 5) + ") rotate(-90)\">" + (fixedWidth - 40) + " cm</text>\n" +
                    "\n" +
                    "\n" +
                    "    <line x1=\"65\"  y1=\"" + (fixedWidth - 35) + "\" x2=\"65\"   y2=\"" + (fixedWidth + 10) + "\"\n" +
                    "          style=\"stroke: #000000;\n" +
                    "\tmarker-start: url(#beginArrow);\n" +
                    "\tmarker-end: url(#endArrow);\"/>\n" +
                    "\n" +
                    "    <text style=\"text-anchor: middle\" transform=\"translate(70," + (fixedWidth - 60) + ") rotate(-90)\">0,45 cm</text>";

        } else {
            svgText = "<line x1=\"40\"  y1=\"10\" x2=\"40\"   y2=\"" + (fixedWidth + 10) + "\"\n" +
                    "          style=\"stroke: #000000;\n" +
                    "\tmarker-start: url(#beginArrow);\n" +
                    "\tmarker-end: url(#endArrow);\"/>\n" +
                    "\n" +
                    "    <text style=\"text-anchor: middle\" transform=\"translate(30," + (fixedWidth/2) + ") rotate(-90)\">" + fixedWidth + " cm</text>";

        }

        return svgText;
    }


    private String vandretMal(){
        String svgText;
        double fixedWidth = width + 100;
        double fixedLength = length + 100;

        if(roof.equals("Peak")){
            svgText = "<line x1=\"75\"  y1=\""+ (fixedWidth - 10) + "\" x2=\""+ (fixedLength - 25) + "\"   y2=\"" + (fixedWidth - 10) + "\"\n" +
                    "          style=\"stroke: #000000;\n" +
                    "\tmarker-start: url(#beginArrow);\n" +
                    "\tmarker-end: url(#endArrow);\"/>\n" +
                    "\n" +
                    "    <text style=\"text-anchor: middle\" transform=\"translate("+ (fixedLength/2 - 15) + ","+ (fixedWidth - 20) + ") rotate(0)\">"+ (length) + " cm</text>";


            svgText = svgText  + "<line x1=\"" + (fixedLength - 280) + "\"  y1=\"" + (fixedWidth - 50) + "\" x2=\"" + (fixedLength - 170) + "\"  y2=\"" + (fixedWidth - 50) + "\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n" +
                    "        <text style=\"text-anchor: middle\" transform=\"translate(" + (fixedLength - 230) + "," + (fixedWidth - 65) + ") rotate(0)\">110cm</text>\n" +
                    "\n" +
                    "        <line x1=\"" + (fixedLength - 170) + "\"  y1=\"" + (fixedWidth - 50) + "\" x2=\"" + (fixedLength - 60) + "\"  y2=\"" + (fixedWidth - 50) + "\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n" +
                    "        <text style=\"text-anchor: middle\" transform=\"translate(" + (fixedLength - 120) + "," + (fixedWidth - 65) + ") rotate(0)\">110cm</text>";

            //Vandret spær 0,89m
            int antalSpear = (int) Math.floor((length - (110 + 110 + 30))/ 89);

            int rest = (int) ((length) - ( (110 + 110 + 30) + (antalSpear * 89)));
            double startPosition = rest + 75;

            for(double x = 0 ; x < (antalSpear) ; x++){

                svgText = svgText + "<line x1=\"" + (startPosition + (x * 89)) + "\"  y1=\"" + (fixedWidth - 50) + "\" x2=\"" + (startPosition + (x * 89) + 89) + "\"  y2=\"" + (fixedWidth - 50) + "\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n" +
                        "    <text style=\"text-anchor: middle\" transform=\"translate(" + (startPosition + (x * 89) + 50) + "," + (fixedWidth - 65) + ") rotate(0)\">89cm</text>";


            }

            if(rest > 0){
                svgText = svgText + "<line x1=\"" + (75) + "\"  y1=\"" + (fixedWidth - 50) + "\" x2=\"" + (startPosition) + "\"  y2=\"" + (fixedWidth - 50) + "\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n" +
                        "    <text style=\"text-anchor: middle\" transform=\"translate(" + (75 + rest/2) + "," + (fixedWidth - 65) + ") rotate(0)\">" + (rest) + "cm</text>";
            }

        }else {
            svgText = "<line x1=\"75\"  y1=\""+ (fixedWidth - 30) + "\" x2=\"" + (fixedLength - 25) + "\"   y2=\"" + (fixedWidth - 30) + "\"\n" +
                    "          style=\"stroke: #000000;\n" +
                    "\tmarker-start: url(#beginArrow);\n" +
                    "\tmarker-end: url(#endArrow);\"/>\n" +
                    "\n" +
                    "    <text style=\"text-anchor: middle\" transform=\"translate(" + ((fixedLength/2) + 25) + "," + (fixedWidth - 15) + ") rotate(0)\">" + (length) + " cm</text>";
        }

        return svgText;
    }

    private String carportHeader() {
        String svgText;

        if (roof.equals("Peak")) {
            svgText = "<svg version=\"1.1\"\n" +
                    "         xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "         xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                    "         x=\"75\"\n" +
                    "         y=\"10\"\n" +
                    "         width=\""  + (length) + "\"\n" +
                    "         height=\"" + (width + 100) + "\"\n" +
                    "         viewBox=\"0 0 "  + (length) + " " + (width + 100) + "\"\n" +
                    "         preserveAspectRatio=\"xMinYMin\">";

        } else {
            svgText = "<svg version=\"1.1\"\n" +
                    "         xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "         xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                    "         x=\"75\"\n" +
                    "         y=\"10\"\n" +
                    "         width=\""  + (length) + "\"\n" +
                    "         height=\"" + (width + 100) + "\"\n" +
                    "         viewBox=\"0 0 "+ (length) + " " + (width + 100) + "\"\n" +
                    "         preserveAspectRatio=\"xMinYMin\">";
        }

        return svgText;
    }


    private String carportSpaer() {
        String svgText;

        if (roof.equals("Peak")) {
            //Først de spær der er 1,10m
            svgText = "<rect x=\"" + (length - 30) + "\" y=\"0\" height=\"" + (width) + "\" width=\"4.5\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 140) + "\" y=\"0\" height=\"" + (width) + "\" width=\"4.5\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 250) + "\" y=\"0\" height=\"" + (width) + "\" width=\"4.5\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";


            //lodret spær 0,89m
            int antalSpear = (int) Math.floor((length - (110 + 110 + 30))/ 89);

            int rest = (int) ((length) - ( (110 + 110 + 30) + (antalSpear * 89)));
            double startPosition = rest;

            for(double x = 0 ; x < (antalSpear) ; x++){

                svgText = svgText + "<rect x=\"" + (startPosition + (x * 89)) + "\" y=\"0\" height=\"" + (width) + "\" width=\"4.5\"\n" +
                        "              style=\"stroke:#000000; fill: #fff\"/>";

            }

            //Vandret midter spær
            svgText = svgText + "<rect x=\"0\" y=\"" + (width/2 - 2) + "\" height=\"4\" width=\"" + (length) + "\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";


            //Udfyldning af midter spær
            int antalVandretspear = (int) Math.floor((width/2) / 27);

            for(double x = 0 ; x <= (antalVandretspear) ; x++){

                svgText = svgText + "<rect x=\"0\" y=\"" + (0 + (x * 27)) + "\" height=\"4\" width=\"" + (length) + "\"\n" +
                        "              style=\"stroke:#000000; fill: #fff\"/>";

            }

            for(double x = 0 ; x <= (antalVandretspear) ; x++){

                svgText = svgText + "<rect x=\"0\" y=\"" + ((width - 4) - (x * 27)) + "\" height=\"4\" width=\"" + (length) + "\"\n" +
                        "              style=\"stroke:#000000; fill: #fff\"/>";

            }


            svgText = svgText + "";

        } else {
            svgText = "";
        }

        return svgText;
    }

    private String carportSpaerMal() {
        String svgText;

        if (roof.equals("Peak")) {

            svgText = "";

        } else {
            svgText = "";

            //Runder NED til nærmeste hele
            int antalSpear = (int) Math.floor((length - 10)/ 55);
            int rest = (int) ((length - 10) - (antalSpear * 55));

            if(rest > 0){
                svgText = "<line x1=\"10\"  y1=\"" + (width + 20) + "\" x2=\"" + (rest + 10) + "\"  y2=\"" + (width + 20) + "\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n" +
                        "        <text style=\"text-anchor: middle\" transform=\"translate("+ (rest/2 + 12) + ","+ (width + 40) + ") rotate(0)\">"+ (rest) + "cm</text>";

            }

            double startPosition = 10 + rest;

            for(double x = 0 ; x < (antalSpear) ; x++){

                svgText = svgText + "<line x1=\"" + (startPosition + (x * 55)) + "\"  y1=\"" + (width + 20) + "\" x2=\"" + (startPosition + (x * 55) + 55) + "\"  y2=\""  + (width + 20) + "\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n" +
                        "        <text style=\"text-anchor: middle\" transform=\"translate(" + (startPosition + (x * 55) + 32) + ","  + (width + 40) + ") rotate(0)\">55cm</text>";

            }

        }

        return svgText;
    }


    private String carportRammeogSider() {
        String svgText;

        if (roof.equals("Peak")) {
            svgText = "<rect x=\"0\" y=\"0\" height=\"" + (width) + "\" width=\"" + (length) + "\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

            svgText = svgText + "<rect x=\"30\" y=\"17\" height=\"4.5\" width=\""  + (length - 60) + "\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"30\" y=\""  + (width - 20) + "\" height=\"4.5\" width=\""  + (length - 60) + "\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

        } else {
            svgText = "<rect x=\"0\" y=\"0\" height=\"" + (width) + "\" width=\"" + (length) + "\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

            svgText = svgText + "<rect x=\"0\" y=\"35\" height=\"4.5\" width=\""  + (length) + "\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"0\" y=\""  + (width - 35) + "\" height=\"4.5\" width=\""  + (length) + "\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

        }

        return svgText;
    }


    private String carportKryds() {
        String svgText;

        if (roof.equals("Peak")) {
            svgText = "<rect x=\"0\" y=\"" + (width/2) + "\" height=\"" + (width/2) + "\" width=\"4\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "        <rect x=\"0\" y=\"0\" height=\"" + (width/2) + "\" width=\"4\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 4) + "\" y=\"0\" height=\"" + (width/2) + "\" width=\"4\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "        <rect x=\"" + (length - 4) + "\" y=\"" + (width/2) + "\" height=\"" + (width/2) + "\" width=\"4\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

        } else {
            svgText = "<line x1=\"60\" y1=\"35\" x2=\"" + (length - 240) + "\" y2=\"" + (width - 30) + "\"  style=\"stroke:#000000; stroke-dasharray: 10,10\"/>\n" +
                    "\n" +
                    "        <line x1=\"60\" y1=\"" + (width - 30) + "\" x2=\"" + (length - 240) + "\" y2=\"35\"  style=\"stroke:#000000; stroke-dasharray: 10,10\"/>";

        }

        return svgText;
    }


    private String carportBagForkant() {
        String svgText;

        if (roof.equals("Peak")) {
            svgText = "";

        } else {
            svgText = "<rect x=\"0\" y=\"0\" height=\"" + (width) + "\" width=\"4\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 4) + "\" y=\"0\" height=\"" + (width) + "\" width=\"4\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";
        }

        return svgText;
    }


    private String carportSpear() {
        String svgText;

        if (roof.equals("Peak")) {
            svgText = "";

        } else {
            svgText = "";

            //Runder NED til nærmeste hele
            int antalSpear = (int) Math.floor((length - 10)/ 55);
            int rest = (int) ((length - 10) - (antalSpear * 55));

            double startPosition;

            if(rest > 0) {
                startPosition = rest + 5;
            } else {
                startPosition = 60;
            }

            for(double x = 0 ; x < (antalSpear) ; x++){

                svgText = svgText + "<rect x=\""+ (startPosition + (x * 55)) + "\" y=\"0\" height=\"" + (width) + "\" width=\"4.5\"\n" +
                        "              style=\"stroke:#000000; fill: #fff\"/>";

            }
        }

        return svgText;
    }


    private String carportStolper() {
        String svgText;

        if (roof.equals("Peak")) {
            svgText = "<rect x=\"80\" y=\"14\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 365) + "\" y=\"14\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 30) + "\" y=\"14\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

            svgText = svgText + "<rect x=\"80\" y=\"" + (width - 22) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 365) + "\" y=\"" + (width - 22) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 30) + "\" y=\"" + (width - 22) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";


        } else {
            svgText = "<rect x=\"" + (112) + "\" y=\"32.5\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 370) + "\" y=\"32.5\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 36) + "\" y=\"32.5\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

            svgText = svgText + "<rect x=\"" + (112) + "\" y=\"" + (width - 37.5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 370) + "\" y=\"" + (width - 37.5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 36) + "\" y=\"" + (width - 37.5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

        }

        return svgText;
    }


    private String carporFooter() {
        String svgText;

        svgText = "</svg>";

        return svgText;
    }


    private String skur(){
        String svgText;

        if(roof.equals("Peak")){
            svgText = "<svg version=\"1.1\"\n" +
                    "         xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "         xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                    "         x=\"75\"\n" +
                    "         y=\"10\"\n" +
                    "         width=\"" + (length) + "\"\n" +
                    "         height=\"" + (width) + "\"\n" +
                    "         viewBox=\"0 0 " + (length) + " " + (width) + "\"\n" +
                    "         preserveAspectRatio=\"xMinYMin\">";

            //Stolper
            svgText = svgText + "<rect x=\"" + (length - shedLength - 25) + "\" y=\"14\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "        \n" +
                    "        <rect x=\"" + (length - shedLength - 25) + "\" y=\"" + (width/2 - 5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - 30) + "\" y=\"" + (width/2 - 5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

            if(width == shedWidth + 40){
                svgText = svgText + "<rect x=\"" + (length - shedLength - 25) + "\" y=\"" + (width - 22) + "\" height=\"9.7\" width=\"9.7\"\n" +
                        "              style=\"stroke:#000000; fill: #fff\"/>";
            }

            //Ramme om skur
            if(width == shedWidth + 40){
                svgText = svgText + "<rect x=\"" + (length - shedLength - 23) + "\" y=\"14\" height=\"" + (width - 28) + "\" width=\"" + (shedLength) + "\"\n" +
                        "              style=\"stroke:#000000; stroke-dasharray: 10,10;\" fill=\"none\"/>";
            } else {
                svgText = svgText + "<rect x=\"" + (length - shedLength - 23) + "\" y=\"14\" height=\"" + (width/2 - 10) + "\" width=\"" + (shedLength) + "\"\n" +
                        "              style=\"stroke:#000000; stroke-dasharray: 10,10;\" fill=\"none\"/>";
            }


            svgText = svgText + "</svg>";

        }else {
            svgText = "<svg version=\"1.1\"\n" +
                    "         xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "         xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
                    "         x=\"75\"\n" +
                    "         y=\"10\"\n" +
                    "         width=\"" + (length) + "\"\n" +
                    "         height=\"" + (width) + "\"\n" +
                    "         viewBox=\"0 0 " + (length) + " " + (width) + "\"\n" +
                    "         preserveAspectRatio=\"xMinYMin\">";


            //Stolper
            svgText = svgText + "<rect x=\"" + (length - shedLength - 18) + "\" y=\"32.5\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "        \n" +
                    "        <rect x=\"" + (length - 35) + "\" y=\"" + (width/2 - 5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>\n" +
                    "\n" +
                    "        <rect x=\"" + (length - shedLength - 18) + "\" y=\"" + (width/2 - 5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                    "              style=\"stroke:#000000; fill: #fff\"/>";

            if(width == shedWidth +  75){
                svgText = svgText + "<rect x=\"" + (length - shedLength - 18) + "\" y=\"" + (width - 37.5) + "\" height=\"9.7\" width=\"9.7\"\n" +
                        "              style=\"stroke:#000000; fill: #fff\"/>";
            }

            //Ramme om skur
            if(width == shedWidth + 75){
                svgText = svgText + "<rect x=\"" + (length - shedLength - 18) + "\" y=\"32.5\" height=\"" + (width - 60) + "\" width=\"" + (shedLength - 7) + "\"\n" +
                        "              style=\"stroke:#000000; stroke-dasharray: 10,10;\" fill=\"none\"/>";
            } else {
                svgText = svgText + "<rect x=\"" + (length - shedLength - 18) + "\" y=\"32.5\" height=\"" + (width/2 - 25) + "\" width=\"" + (shedLength - 7) + "\"\n" +
                        "              style=\"stroke:#000000; stroke-dasharray: 10,10;\" fill=\"none\"/>";
            }

            svgText = svgText + "</svg>";
        }
        return svgText;
    }


    private String footer(){
        String svgText;

        svgText = "</svg>";

        return svgText;
    }
}
