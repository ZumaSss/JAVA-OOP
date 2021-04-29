package Exceptions;

public class WrongInputFormat extends Exception{
    public WrongInputFormat () {
        super();
    }
    public WrongInputFormat (String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
