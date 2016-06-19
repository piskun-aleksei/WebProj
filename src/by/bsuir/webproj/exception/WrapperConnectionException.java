package by.bsuir.webproj.exception;

/**
 * Created by Алексей on 17.04.2016.
 */
public class WrapperConnectionException extends Exception {
    public WrapperConnectionException() {
        super();
    }

    public WrapperConnectionException(String message) {
        super(message);
    }

    public WrapperConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrapperConnectionException(Throwable cause) {
        super(cause);
    }

    protected WrapperConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
