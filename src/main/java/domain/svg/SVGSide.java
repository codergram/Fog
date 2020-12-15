/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.svg;

import domain.carport.Carport;

public class SVGSide {

  private final double length;
  private final double height;
  private final String roof;
  private final boolean withShed;
  private final StringBuilder sb;
  private final boolean isCustomer;
  private String svgCode;
  private double shedLength;

  public SVGSide(Carport carport, boolean isCustomer) {
    this.length = carport.getLength();
    this.height = getCarportHeight(carport.getRoofType().name());
    this.withShed = carport.hasShed();
    this.roof = carport.getRoofType().name();
    this.sb = new StringBuilder();
    this.svgCode = null;
    this.shedLength = 0.0;
    if (carport.getShed() != null) {
      this.shedLength = carport.getShed().getLength();
    }
    this.isCustomer = isCustomer;
  }

  private double getCarportHeight(String roof) {
    if (roof.equals("Peak")) {
      return 305;
    } else {
      return 230;
    }
  }

  public String getSvgCode() {
    sb.append(createHeader());
    if (!isCustomer) {
      sb.append(lodretMal());
      sb.append(vandretMal());
    }
    if (withShed) {
      sb.append(skur());
    }
    sb.append(carportHeader());
    sb.append(carportStolper());
    sb.append(carportTag());
    sb.append(footer());
    sb.append(footer());
    svgCode = sb.toString();
    return svgCode;
  }

  private String createHeader() {
    // først " og så ).append().append("
    // først " og så  + () + "
    String svgText;
    double headerHeight = height + 100;
    double headerLength = length + 150;

    if (roof.equals("Peak")) {
      svgText =
          "<svg version=\"1.1\"\n"
              + "     xmlns=\"http://www.w3.org/2000/svg\"\n"
              + "     height=\"100%\"  width=\"100%\" viewBox=\"0 0 "
              + headerLength
              + " "
              + headerHeight
              + "\"\n"
              + "     preserveAspectRatio=\"xMinYMin\" "
              + "     style=\"width:100%; height:100%\">\n"
              + "\n"
              + "    <!--    Hvid ramme-->\n"
              + "    <rect x=\"0\" y=\"0\" height=\""
              + headerHeight
              + "\" width=\""
              + headerLength
              + "\"\n"
              + "          style=\"stroke:#fff; fill: #fff\"/>\n"
              + "\n"
              + "\n"
              + "\n"
              + "    <defs>\n"
              + "        <marker\n"
              + "                id=\"beginArrow\"\n"
              + "                markerWidth=\"12\"\n"
              + "                markerHeight=\"12\"\n"
              + "                refX=\"0\"\n"
              + "                refY=\"6\"\n"
              + "                orient=\"auto\">\n"
              + "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n"
              + "        </marker>\n"
              + "        <marker\n"
              + "                id=\"endArrow\"\n"
              + "                markerWidth=\"12\"\n"
              + "                markerHeight=\"12\"\n"
              + "                refX=\"12\"\n"
              + "                refY=\"6\"\n"
              + "                orient=\"auto\">\n"
              + "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n"
              + "        </marker>\n"
              + "    </defs>";

    } else {
      svgText =
          "<svg version=\"1.1\"\n"
              + "     xmlns=\"http://www.w3.org/2000/svg\"\n"
              + "     height=\"100%\"  width=\"100%\" viewBox=\"0 0 "
              + headerLength
              + " "
              + headerHeight
              + "\"\n"
              + "     preserveAspectRatio=\"xMinYMin\">\n"
              + "\n"
              + "    <!--    Hvid ramme (heøjde + 100 og bredde +150)-->\n"
              + "    <rect x=\"0\" y=\"0\" height=\""
              + headerHeight
              + "\" width=\""
              + headerLength
              + "\"\n"
              + "          style=\"stroke:#fff; fill: #fff\"/>\n"
              + "\n"
              + "\n"
              + "\n"
              + "    <defs>\n"
              + "        <marker\n"
              + "                id=\"beginArrow\"\n"
              + "                markerWidth=\"12\"\n"
              + "                markerHeight=\"12\"\n"
              + "                refX=\"0\"\n"
              + "                refY=\"6\"\n"
              + "                orient=\"auto\">\n"
              + "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n"
              + "        </marker>\n"
              + "        <marker\n"
              + "                id=\"endArrow\"\n"
              + "                markerWidth=\"12\"\n"
              + "                markerHeight=\"12\"\n"
              + "                refX=\"12\"\n"
              + "                refY=\"6\"\n"
              + "                orient=\"auto\">\n"
              + "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n"
              + "        </marker>\n"
              + "    </defs>";
    }

    return svgText;
  }

  private String lodretMal() {
    String svgText;
    double fixedLength = length + 150;

    if (roof.equals("Peak")) {
      svgText =
          "<line x1=\"20\"  y1=\"10\" x2=\"20\"   y2=\"315\"\n"
              + "          style=\"stroke: #000000;\n"
              + "\tmarker-start: url(#beginArrow);\n"
              + "\tmarker-end: url(#endArrow);\"/>\n"
              + "\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate(15,135) rotate(-90)\">305 cm</text>\n"
              + "\n"
              + "\n"
              + "    <!--    Lodret måling-->\n"
              + "    <line x1=\"50\"  y1=\"105\" x2=\"50\"   y2=\"315\"\n"
              + "          style=\"stroke: #000000;\n"
              + "\tmarker-start: url(#beginArrow);\n"
              + "\tmarker-end: url(#endArrow);\"/>\n"
              + "\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate(45,210) rotate(-90)\">210 cm</text>";

    } else {
      svgText =
          "   <!--    Lodret måling-->\n"
              + "    <line x1=\"20\"  y1=\"10\" x2=\"20\"   y2=\"250\"\n"
              + "          style=\"stroke: #000000;\n"
              + "\tmarker-start: url(#beginArrow);\n"
              + "\tmarker-end: url(#endArrow);\"/>\n"
              + "\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate(15,135) rotate(-90)\">230 cm</text>\n"
              + "\n"
              + "\n"
              + "    <!--    Lodret måling-->\n"
              + "    <line x1=\"50\"  y1=\"30\" x2=\"50\"   y2=\"250\"\n"
              + "          style=\"stroke: #000000;\n"
              + "\tmarker-start: url(#beginArrow);\n"
              + "\tmarker-end: url(#endArrow);\"/>\n"
              + "\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate(45,135) rotate(-90)\">210 cm</text>\n"
              + "\n"
              + "    <!--    Lodret måling-->\n"
              + "    <line x1=\""
              + (fixedLength - 30)
              + "\"  y1=\"20\" x2=\""
              + (fixedLength - 30)
              + "\"   y2=\"250\"\n"
              + "          style=\"stroke: #000000;\n"
              + "\tmarker-start: url(#beginArrow);\n"
              + "\tmarker-end: url(#endArrow);\"/>\n"
              + "\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (fixedLength - 40)
              + ",135) rotate(-90)\">220 cm</text>";
    }

    return svgText;
  }

  private String vandretMal() {
    String svgText;
    double headerLength = length + 150;

    if (roof.equals("Peak")) {
      double startst = 75;
      double st1 = 80;
      double st3 = 120;
      double st4 = 225;
      double st5 = 30;
      double st2 = length - (st1 + st3 + st4 + st5);

      svgText =
          "<line x1=\""
              + startst
              + "\"  y1=\"370\" x2=\""
              + (startst + st1)
              + "\"  y2=\"370\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (startst + 50)
              + ",360) rotate(0)\">"
              + (st1)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startst + st1)
              + "\"  y1=\"370\" x2=\""
              + (startst + st1 + st2)
              + "\"  y2=\"370\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (startst + st1 + (st2 / 2))
              + ",360) rotate(0)\">"
              + (st2)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startst + st1 + st2)
              + "\"  y1=\"370\" x2=\""
              + (startst + st1 + st2 + st3)
              + "\"  y2=\"370\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (headerLength - 380)
              + ",360) rotate(0)\">"
              + (st3)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startst + st1 + st2 + st3)
              + "\"  y1=\"370\" x2=\""
              + (startst + st1 + st2 + st3 + st4)
              + "\"  y2=\"370\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (headerLength - 200)
              + ",360) rotate(0)\">"
              + (st4)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startst + st1 + st2 + st3 + st4)
              + "\"  y1=\"370\" x2=\""
              + (startst + st1 + st2 + st3 + st4 + st5)
              + "\"  y2=\"370\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (headerLength - 80)
              + ",360) rotate(0)\">"
              + (st5)
              + " cm</text>";

    } else {
      double startft = 75;
      double ft1 = 100;
      double ft3 = 130;
      double ft4 = 210;
      double ft5 = 30;
      double ft2 = length - (ft1 + ft3 + ft4 + ft5);

      svgText =
          "<line x1=\""
              + startft
              + "\"  y1=\"290\" x2=\""
              + (startft + ft1)
              + "\"  y2=\"290\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (startft + 50)
              + ",280) rotate(0)\">"
              + (ft1)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startft + ft1)
              + "\"  y1=\"290\" x2=\""
              + (startft + ft1 + ft2)
              + "\"  y2=\"290\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (startft + ft1 + (ft2 / 2))
              + ",280) rotate(0)\">"
              + (ft2)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startft + ft1 + ft2)
              + "\"  y1=\"290\" x2=\""
              + (startft + ft1 + ft2 + ft3)
              + "\"  y2=\"290\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (headerLength - 380)
              + ",280) rotate(0)\">"
              + (ft3)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startft + ft1 + ft2 + ft3)
              + "\"  y1=\"290\" x2=\""
              + (startft + ft1 + ft2 + ft3 + ft4)
              + "\"  y2=\"290\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (headerLength - 210)
              + ",280) rotate(0)\">"
              + (ft4)
              + " cm</text>\n"
              + "\n"
              + "    <line x1=\""
              + (startft + ft1 + ft2 + ft3 + ft4)
              + "\"  y1=\"290\" x2=\""
              + (startft + ft1 + ft2 + ft3 + ft4 + ft5)
              + "\"  y2=\"290\" style=\"stroke: #000000; marker-start: url(#beginArrow);marker-end: url(#endArrow);\"/>\n"
              + "    <text style=\"text-anchor: middle\" transform=\"translate("
              + (headerLength - 90)
              + ",280) rotate(0)\">"
              + (ft5)
              + " cm</text>";
    }

    return svgText;
  }

  private String skur() {
    StringBuilder svgText;

    if (roof.equals("Peak")) {
      svgText =
          new StringBuilder(
              "<svg version=\"1.1\"\n"
                  + "         xmlns=\"http://www.w3.org/2000/svg\"\n"
                  + "         xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                  + "         x=\"75\"\n"
                  + "         y=\"10\"\n"
                  + "         width=\""
                  + (length)
                  + "\"\n"
                  + "         height=\""
                  + (height)
                  + "\"\n"
                  + "         viewBox=\"0 0 "
                  + (length)
                  + " "
                  + (height)
                  + "\"\n"
                  + "         preserveAspectRatio=\"xMinYMin\">");

      for (double x = 0; x < 210; x = x + 10) {
        svgText
            .append("<rect x=\"")
            .append((length - shedLength - 20) + x)
            .append("\" y=\"95\" height=\"210\" width=\"10\"\n")
            .append("style=\"stroke:#000000; \" fill=\"none\"/>");
      }

      svgText.append("</svg>");

    } else {
      svgText =
          new StringBuilder(
              "<svg version=\"1.1\"\n"
                  + "         xmlns=\"http://www.w3.org/2000/svg\"\n"
                  + "         xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                  + "         x=\"75\"\n"
                  + "         y=\"10\"\n"
                  + "         width=\""
                  + (length)
                  + "\"\n"
                  + "         height=\""
                  + (height)
                  + "\"\n"
                  + "         viewBox=\"0 0 "
                  + (length)
                  + " "
                  + (height)
                  + "\"\n"
                  + "         preserveAspectRatio=\"xMinYMin\">");

      for (double x = 0; x < 220; x = x + 10) {
        svgText
            .append("<rect x=\"")
            .append((length - shedLength - 30) + x)
            .append("\" y=\"30\" height=\"200\" width=\"10\"\n")
            .append("style=\"stroke:#000000; \" fill=\"none\"/>");
      }

      svgText.append("</svg>");
    }
    return svgText.toString();
  }

  private String carportHeader() {
    return "<svg version=\"1.1\"\n"
        + "         xmlns=\"http://www.w3.org/2000/svg\"\n"
        + "         xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
        + "         x=\"75\"\n"
        + "         y=\"10\"\n"
        + "         width=\""
        + (length)
        + "\"\n"
        + "         height=\""
        + (height)
        + "\"\n"
        + "         viewBox=\"0 0 "
        + (length)
        + " "
        + (height)
        + "\"\n"
        + "         preserveAspectRatio=\"xMinYMin\">";
  }

  private String carportStolper() {
    String svgText;

    if (roof.equals("Peak")) {
      svgText =
          "<rect x=\"80\" y=\"95\" height=\"210\" width=\"10\"\n"
              + "              style=\"stroke:#000000; fill: #fff\"/>\n"
              + "\n"
              + "        <rect x=\""
              + (length - 375)
              + "\" y=\"95\" height=\"210\" width=\"10\"\n"
              + "              style=\"stroke:#000000; fill: #fff\"/>\n"
              + "\n"
              + "        <rect x=\""
              + (length - 40)
              + "\" y=\"95\" height=\"210\" width=\"10\"\n"
              + "              style=\"stroke:#000000; fill: #fff\"/>";

    } else {
      svgText =
          "<rect x=\"100\" y=\"10\" height=\"220\" width=\"10\"\n"
              + "              style=\"stroke:#000000; fill: #fff\"/>\n"
              + "\n"
              + "        <rect x=\""
              + (length - 370)
              + "\" y=\"20\" height=\"210\" width=\"10\"\n"
              + "              style=\"stroke:#000000; fill: #fff\"/>\n"
              + "\n"
              + "        <rect x=\""
              + (length - 30)
              + "\" y=\"20\" height=\"210\" width=\"10\"\n"
              + "              style=\"stroke:#000000; fill: #fff\"/>";
    }

    return svgText;
  }

  private String carportTag() {
    StringBuilder svgText;

    if (roof.equals("Peak")) {
      svgText =
          new StringBuilder(
              "<rect x=\"5\" y=\"15\" height=\"65\" width=\"5\"\n"
                  + "              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>");

      for (double x = 0; x < ((length - 10) / 10); x++) {
        svgText
            .append("<rect x=\"")
            .append(10 + (x * 10))
            .append("\" y=\"15\" height=\"65\" width=\"10\"\n")
            .append("style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>");
      }

      svgText
          .append("<rect x=\"0\" y=\"5\" height=\"10\" width=\"")
          .append(length)
          .append("\"\n")
          .append("style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>");

      svgText
          .append("<rect x=\"0\" y=\"80\" height=\"15\" width=\"")
          .append(length)
          .append("\"\n")
          .append("              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>\n")
          .append("        <rect x=\"0\" y=\"85\" height=\"10\" width=\"")
          .append(length)
          .append("\"\n")
          .append("              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>\n")
          .append("        <rect x=\"35\" y=\"95\" height=\"10\" width=\"")
          .append(length - 65)
          .append("\"\n")
          .append("              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>");

      svgText
          .append("<rect x=\"0\" y=\"0\" height=\"70\" width=\"10\"\n")
          .append("              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>\n")
          .append("        <rect x=\"")
          .append(length - 10)
          .append("\" y=\"0\" height=\"70\" width=\"10\"\n")
          .append("              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(0)\"/>");

    } else {
      // minus 1 pixel da rammen er for lille pga tag drejning
      svgText =
          new StringBuilder(
              "<rect x=\"1\" y=\"0\" height=\"20\" width=\""
                  + (length - 1)
                  + "\"\n"
                  + "              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(1)\"/>\n"
                  + "\n"
                  + "        <rect x=\"1\" y=\"20\" height=\"20\" width=\""
                  + (length - 1)
                  + "\"\n"
                  + "              style=\"stroke:#000000; fill: #fff\" transform=\"rotate(1)\"/>");
    }

    return svgText.toString();
  }

  private String footer() {
    return "</svg>";
  }
}
