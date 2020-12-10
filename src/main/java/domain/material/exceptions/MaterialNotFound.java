/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.material.exceptions;

public class MaterialNotFound extends Exception {
  public MaterialNotFound() {
    System.out.println("Material not found");
  }

  public MaterialNotFound(String message) {
    super(message);
  }
}
