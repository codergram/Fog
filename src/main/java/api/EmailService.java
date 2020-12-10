/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package api;

import api.exceptions.EmailNotSent;
import java.io.File;

/** Interface for emailing service */
public interface EmailService {
  void sendEmail(String toAddress, String subject, String message, File file) throws EmailNotSent;
}
