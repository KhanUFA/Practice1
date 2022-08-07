package some.exceptions;

public class ItemAlreadyPlacedException extends RuntimeException{
    public ItemAlreadyPlacedException(String message) {
        super(message);
    }
}
