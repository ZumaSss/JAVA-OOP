package Exceptions;

public class WrongParamsQuantity extends Exception{
    public WrongParamsQuantity() {
        super();
    }
    public WrongParamsQuantity(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
