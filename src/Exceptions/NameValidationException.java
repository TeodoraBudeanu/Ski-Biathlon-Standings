package Exceptions;

public class NameValidationException extends Exception {
    public NameValidationException(String errorMessage){
        super(errorMessage);
    }
}
