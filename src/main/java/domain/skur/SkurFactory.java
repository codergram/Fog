package domain.skur;

import core.Skur;
import infrastructure.DBexception;

public interface SkurFactory {

    Skur createSkur(Skur skur) throws DBexception;

}
