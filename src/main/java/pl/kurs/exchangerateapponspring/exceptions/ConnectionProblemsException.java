package pl.kurs.exchangerateapponspring.exceptions;

public class ConnectionProblemsException extends Exception{

    public ConnectionProblemsException(String message) {
        super(message);
    }

    public ConnectionProblemsException(String message, Throwable cause) {
        super(message, cause);
    }
}
