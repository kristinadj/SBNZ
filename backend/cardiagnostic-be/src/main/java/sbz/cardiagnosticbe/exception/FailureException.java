package sbz.cardiagnosticbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailureException extends RuntimeException {

    public FailureException(String msg) {
        super(msg);
    }
}
