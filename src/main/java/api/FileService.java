package api;

import api.exceptions.PDFNotCreated;
import domain.order.Order;

import java.io.File;

public interface FileService {
    File generatePdf(String path, Order order) throws PDFNotCreated;
}
