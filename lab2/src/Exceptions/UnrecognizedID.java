package Exceptions;

public class UnrecognizedID extends Exception{
    public UnrecognizedID () {
        super();
    }
    public UnrecognizedID (String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
