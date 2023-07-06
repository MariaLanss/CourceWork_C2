package pro.sky.coursework_c2.exceptions;

public class WrongAmountException extends RuntimeException {
    public WrongAmountException(String message) {
        super(message);
    }
}