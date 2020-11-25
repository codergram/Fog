package domain.material.exceptions;

public class MaterialNotFound extends Exception{
    public MaterialNotFound() {
        System.out.println("Material not found");
    }
    
    public MaterialNotFound(String message) {
        super(message);
    }
}
