package api;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String removeHtml(String src) {
        return src.replaceAll("\"[^\"]*+\"", "");
    }
    
    public static String fileToString(String file){
        if(file == null || file.isEmpty()) return null;
        try (InputStream input = Api.class.getClassLoader().getResourceAsStream(file)) {
            return IOUtils.toString(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
