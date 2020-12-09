package domain.partslist.exceptions;

import domain.partslist.Part;

import java.util.List;

public interface PartslistFactory {
  List<Part> createPartsList();
}
