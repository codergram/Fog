package web.website;

import org.slf4j.Logger;
import web.BaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.slf4j.LoggerFactory.getLogger;


@WebServlet("/GetPDF/*")
public class GetPDF extends BaseServlet {
    
    private static final Logger log = getLogger(GetPDF.class);
    
    /**
     * Renders the index.jsp page
     * @see BaseServlet
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
                String uuidReq = req.getPathInfo().substring(1);
            
                log.info("UUID requested: " + uuidReq);
               
                
                File pdfToDownload = api.getPdf(String.valueOf(uuidReq));
                
                try{
                    downloadFile(req,resp,pdfToDownload);
                } catch ( IOException e){
                    log.error(e.getMessage());
                }
            
        } catch (Exception e){
            log(e.getMessage());
        }
    }
    
    private void downloadFile(HttpServletRequest req, HttpServletResponse resp, File file) throws IOException {
        // reads input file from an absolute path
        File downloadFile = file;
        FileInputStream inStream = new FileInputStream(downloadFile);
    
        Path path = downloadFile.toPath();
        String mimeType = Files.probeContentType(path);
        
        // obtains ServletContext
        ServletContext context = getServletContext();
    
        // gets MIME type of the file
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
    
        // modifies response
        resp.setContentType(mimeType);
        resp.setContentLength((int) downloadFile.length());
    
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        resp.setHeader(headerKey, headerValue);
    
        // obtains response's output stream
        OutputStream outStream = resp.getOutputStream();
    
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
    
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
    
        inStream.close();
        outStream.close();
    }
}
