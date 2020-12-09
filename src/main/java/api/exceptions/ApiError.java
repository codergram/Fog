package api.exceptions;

public class ApiError extends Exception {
    public ApiError() {
    }

    public ApiError(String message) {
        super(message);
    }
    
    public ApiError(Throwable cause) {
        super(cause);
    }
}
