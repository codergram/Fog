/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package api;

import api.exceptions.PDFNotCreated;
import domain.order.Order;
import infrastructure.exceptions.PDFNotFound;
import java.io.File;

/** Interface for handling local files */
public interface FileService {
  File generatePdf(Order order, String svgSide, String svgTop) throws PDFNotCreated;

  File getPdf(String filename) throws PDFNotFound;
}
