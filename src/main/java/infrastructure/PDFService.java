package infrastructure;
import api.FileService;
import api.Utils;
import api.exceptions.PDFNotCreated;
import domain.order.Order;

import java.io.*;

import com.itextpdf.html2pdf.HtmlConverter;

public class PDFService implements FileService {
    private static final String templateFileName = "pdftemplate.html";
    
    private static final String drawingsPath = "testfiles/";
    
    private String fillTemplate(Order order){
        
        String templateSrc = Utils.fileToString("pdf/"+templateFileName);
        
        templateSrc = templateSrc.replace("$$TEKST$$", "Hejsa");
        
        return templateSrc;
    }
    
    
    @Override
    public File generatePdf(String path, Order order) throws PDFNotCreated {
        try {
            String htmlString = fillTemplate(order);
            
            String fileName = "/" + order.getId() + ".pdf";
            
            File dir = new File(path);
            dir.mkdirs();
    
            File newPdf = new File(path + fileName);
            newPdf.createNewFile(); // if file already exists will do nothing
            FileOutputStream fileOutput = new FileOutputStream(newPdf);
            System.out.println(newPdf.getAbsolutePath());
    
            HtmlConverter.convertToPdf(htmlString, fileOutput);
            
            return newPdf;
            
        } catch (IOException e) {
            throw new PDFNotCreated(e);
        }
    }
}
