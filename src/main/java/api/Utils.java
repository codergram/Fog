/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package api;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

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
