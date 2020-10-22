package monad.example.errors;

public class SimpleError {

    private final String message;

    public SimpleError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
