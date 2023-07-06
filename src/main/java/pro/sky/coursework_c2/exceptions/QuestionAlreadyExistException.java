package pro.sky.coursework_c2.exceptions;

public class QuestionAlreadyExistException extends RuntimeException {
    public QuestionAlreadyExistException(String message) {
        super(message);
    }
}