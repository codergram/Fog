package domain.carport.shed;

public interface ShedFactory {

    Shed createShed(Shed shed) throws ShedException;

}
