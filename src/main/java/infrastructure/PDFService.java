package infrastructure;
import api.FileService;
import api.Utils;
import api.exceptions.PDFNotCreated;
import com.itextpdf.html2pdf.ConverterProperties;
import domain.material.materials.Tree;
import domain.order.Order;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import com.itextpdf.html2pdf.HtmlConverter;
import domain.partslist.Part;
import infrastructure.exceptions.PDFNotFound;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.xml.sax.InputSource;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;

import static org.slf4j.LoggerFactory.getLogger;

public class PDFService implements FileService {
    private static final Logger log = getLogger(PDFService.class);
    private static final String templateFileName = "pdftemplate.html";
    private final String fileDir = System.getProperty("java.io.tmpdir");
    
    private String fillTemplate(Order order, String svgSide, String svgTop){
        String templateSrc = Utils.fileToString("pdf/"+templateFileName);
        
        templateSrc = templateSrc.replace("$$ORDRENUMMER$$", String.valueOf(order.getId()));
    
        String svgTopName = "/" + order.getUuid() + "-top.png";
        String svgSideName = "/" + order.getUuid() + "-side.png";
        
        String topPng = "";
        String sidePng = "";
    
        try {
            topPng = "<img src=\"" + convertSVGtoPNG(svgTopName, svgTop) + "\" />";
            sidePng = "<img src=\"" + convertSVGtoPNG(svgSideName, svgSide) + "\" />";
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        templateSrc = templateSrc.replace("$$sideDrawing$$", sidePng);
        templateSrc = templateSrc.replace("$$topDrawing$$", topPng);
        
        StringBuilder parts = new StringBuilder();
        String partRowTemplate = "<tr>\n" +
                "                <td class=\"tg-0lax\">$$MATERIAL$$</td>\n" +
                "                <td class=\"tg-0lax\">$$LENGTH$$</td>\n" +
                "                <td class=\"tg-0lax\">$$AMOUNT$$</td>\n" +
                "                <td class=\"tg-0lax\">$$UNIT$$</td>\n" +
                "                <td class=\"tg-0lax\">$$DESCRIPTION$$</td>\n" +
                "            </tr>";
        
        for(Part p: order.getCarport().getPartslist().getPartList()){
            String part = partRowTemplate.replace("$$MATERIAL$$", p.getMaterial().getName())
                                        .replace("$$AMOUNT$$", String.valueOf(p.getAmount()))
                                        .replace("$$UNIT$$", p.getMaterial().getUnitString())
                                        .replace("$$DESCRIPTION$$", p.getDescription());
            
            if(p.getMaterial() instanceof Tree){
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
    public synchronized File generatePdf(Order order, String svgSide, String svgTop) throws PDFNotCreated {
        try {
            String htmlString = fillTemplate(order, svgSide, svgTop);
            
            String fileName = "/" + order.getUuid() + ".pdf";
            
            File dir = new File(fileDir);
            dir.mkdirs();
    
            File newPdf = new File(fileDir + fileName);
            newPdf.createNewFile(); // if file already exists will do nothing
            FileOutputStream fileOutput = new FileOutputStream(newPdf);
    
            ConverterProperties converterProperties = new ConverterProperties();
            URL resourcesUrl = getClass().getClassLoader().getResource("/pdf/");
            try {
                converterProperties.setBaseUri(resourcesUrl.toURI().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
    
            HtmlConverter.convertToPdf(htmlString, fileOutput, converterProperties);
    
            log.info("PDF Generated: " + newPdf.getAbsolutePath());
            
            return newPdf;
            
        } catch (IOException e) {
            throw new PDFNotCreated(e);
        }
    }
    
    @Override
    public synchronized File getPdf(String filename) throws PDFNotFound {
        try {
            String fullFileName = "/" + filename + ".pdf";
        
            File pdfFile = new File(fileDir + fullFileName);
            
            log.info("PDF downloaded: " + pdfFile.getAbsolutePath());
        
            return pdfFile;
        
        } catch (Exception e) {
            throw new PDFNotFound(e);
        }
    }
    
    @Override
    public String convertSVGtoPNG(String fileName, String svgSource) throws Exception {
        
        InputStream inputStream = new ByteArrayInputStream(svgSource.getBytes(StandardCharsets.UTF_8));
        
        TranscoderInput input = new TranscoderInput(inputStream);
    
        File dir = new File(fileDir);
        dir.mkdirs();
    
        File newPdf = new File(fileDir + fileName);
        newPdf.createNewFile(); // if file already exists will do nothing
        
        
        OutputStream png_ostream = new FileOutputStream(newPdf);
        
        TranscoderOutput png_output = new TranscoderOutput(png_ostream);
        
        PNGTranscoder transcoder = new PNGTranscoder();
//        transcoder.addTranscodingHint(PNGTranscoder.KEY_MAX_HEIGHT, 1500);
//        transcoder.addTranscodingHint(PNGTranscoder.KEY_MAX_WIDTH, 1500);
        
        transcoder.transcode(input, png_output);
        
        png_ostream.flush();
        png_ostream.close();
    
        return newPdf.getAbsolutePath();
        
    }
}
