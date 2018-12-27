package Exceptions;

public class ShootingRangeValidationException extends Exception {
    public ShootingRangeValidationException(String errorMessage){
        super(errorMessage);
    }
}
