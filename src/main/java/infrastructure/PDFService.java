package infrastructure;
import api.FileService;
import api.Utils;
import api.exceptions.PDFNotCreated;
import domain.material.materials.Tree;
import domain.order.Order;

import java.io.*;

import com.itextpdf.html2pdf.HtmlConverter;
import domain.partslist.Part;
import infrastructure.exceptions.PDFNotFound;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class PDFService implements FileService {
    private static final Logger log = getLogger(PDFService.class);
    private static final String templateFileName = "pdftemplate.html";
    private final String fileDir = System.getProperty("java.io.tmpdir");
    
    private static final String drawingsPath = "testfiles/";
    
    private String fillTemplate(Order order, String svgSide, String svgTop){
        String templateSrc = Utils.fileToString("pdf/"+templateFileName);
        
        templateSrc = templateSrc.replace("$$ORDRENUMMER$$", String.valueOf(order.getId()));
        
        //TODO : Fix svg errors.
        templateSrc = templateSrc.replace("$$sideDrawing$$", svgSide);
        templateSrc = templateSrc.replace("$$topDrawing$$", svgTop);
        
        
        StringBuilder parts = new StringBuilder();
        String partRowTemplate = "<tr>\n" +
                "                <td>$$MATERIAL$$</td>\n" +
                "                <td>$$LENGTH$$</td>\n" +
                "                <td>$$AMOUNT$$</td>\n" +
                "                <td>$$UNIT$$</td>\n" +
                "                <td>$$DESCRIPTION$$</td>\n" +
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
    
            HtmlConverter.convertToPdf(htmlString, fileOutput);
    
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
}
