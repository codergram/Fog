/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package infrastructure;

import static org.slf4j.LoggerFactory.getLogger;

import api.FileService;
import api.Utils;
import api.exceptions.ApiError;
import api.exceptions.PDFNotCreated;
import com.itextpdf.html2pdf.HtmlConverter;
import domain.material.materials.Tree;
import domain.order.Order;
import domain.partslist.Part;
import infrastructure.exceptions.PDFNotFound;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;

public class PDFService implements FileService {
  private static final Logger log = getLogger(PDFService.class);
  private static final String templateFileName = "pdftemplate.html";

  private final String fileDir = System.getProperty("java.io.tmpdir");

  private String fillTemplate(Order order, String svgSide, String svgTop) {
    String templateSrc = Utils.fileToString("pdf/" + templateFileName);

    templateSrc = templateSrc.replace("$$ORDRENUMMER$$", String.valueOf(order.getId()));

    String svgTopName = "/" + order.getUuid() + "-top.png";
    String svgSideName = "/" + order.getUuid() + "-side.png";

    String topPng = "";
    String sidePng = "";

    try {
      topPng = "<img src=\"" + convertSVGtoPNG(svgTopName, svgTop) + "\" />";
      sidePng = "<img src=\"" + convertSVGtoPNG(svgSideName, svgSide) + "\" />";
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    templateSrc = templateSrc.replace("$$sideDrawing$$", sidePng);
    templateSrc = templateSrc.replace("$$topDrawing$$", topPng);

    StringBuilder parts = new StringBuilder();
    String partRowTemplate =
        "<tr>\n"
            + "                <td class=\"tg-0lax\">$$MATERIAL$$</td>\n"
            + "                <td class=\"tg-0lax\">$$LENGTH$$</td>\n"
            + "                <td class=\"tg-0lax\">$$AMOUNT$$</td>\n"
            + "                <td class=\"tg-0lax\">$$UNIT$$</td>\n"
            + "                <td class=\"tg-0lax\">$$DESCRIPTION$$</td>\n"
            + "            </tr>";

    for (Part p : order.getCarport().getPartslist().getPartList()) {
      String part =
          partRowTemplate
              .replace("$$MATERIAL$$", p.getMaterial().getName())
              .replace("$$AMOUNT$$", String.valueOf(p.getAmount()))
              .replace("$$UNIT$$", p.getMaterial().getUnitString())
              .replace("$$DESCRIPTION$$", p.getDescription());

      if (p.getMaterial() instanceof Tree) {
        part = part.replace("$$LENGTH$$", ((Tree) p.getMaterial()).getLengthString());
      } else {
        part = part.replace("$$LENGTH$$", "");
      }

      parts.append(part);
    }

    templateSrc = templateSrc.replace("$$PARTROWS$$", parts.toString());

    return templateSrc;
  }

  @Override
  public File generatePdf(Order order, String svgSide, String svgTop) throws PDFNotCreated {
    try {
      String htmlString = fillTemplate(order, svgSide, svgTop);

      String uuid = UUID.randomUUID().toString().replace("-", "");

      String fileName = "/" + uuid + ".pdf";

      File dir = new File(fileDir);
      dir.mkdirs();

      File newPdf = new File(fileDir + fileName);
      newPdf.createNewFile(); // if file already exists will do nothing
      FileOutputStream fileOutput = new FileOutputStream(newPdf);

      HtmlConverter.convertToPdf(htmlString, fileOutput);

      log.info("PDF Generated: {}", newPdf.getAbsolutePath());

      return newPdf;

    } catch (IOException e) {
      throw new PDFNotCreated(e);
    }
  }

  @Override
  public File getPdf(String filename) throws PDFNotFound {
    try {
      String fullFileName = "/" + filename + ".pdf";

      File pdfFile = new File(fileDir + fullFileName);

      log.info("PDF downloaded: {}", pdfFile.getAbsolutePath());

      return pdfFile;

    } catch (Exception e) {
      throw new PDFNotFound(e);
    }
  }

  private String convertSVGtoPNG(String fileName, String svgSource) throws ApiError {
    try {
      InputStream inputStream =
          new ByteArrayInputStream(svgSource.getBytes(StandardCharsets.UTF_8));

      TranscoderInput input = new TranscoderInput(inputStream);

      File dir = new File(fileDir);
      dir.mkdirs();

      File svgFile = new File(fileDir + fileName);
      svgFile.createNewFile(); // if file already exists will do nothing

      OutputStream ostream = new FileOutputStream(svgFile);

      TranscoderOutput output = new TranscoderOutput(ostream);

      PNGTranscoder transcoder = new PNGTranscoder();

      transcoder.transcode(input, output);

      ostream.flush();
      ostream.close();

      return svgFile.getAbsolutePath();
    } catch (Exception ex) {
      throw new ApiError(ex);
    }
  }
}
