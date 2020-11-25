package domain.skur;

import infrastructure.DBException;

public interface SkurFactory {

    Skur createSkur(Skur skur) throws DBException;

}
