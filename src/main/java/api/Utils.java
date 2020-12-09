package api;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

  private Utils() {}

  /** @return File content as String */
  public static String fileToString(String file) {
    if (file == null || file.isEmpty()) return null;
    try (InputStream input = Api.class.getClassLoader().getResourceAsStream(file)) {
      if (input == null) return null;
      return IOUtils.toString(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
