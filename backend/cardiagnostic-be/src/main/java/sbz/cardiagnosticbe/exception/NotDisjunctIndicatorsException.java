package sbz.cardiagnosticbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotDisjunctIndicatorsException extends RuntimeException {

    public NotDisjunctIndicatorsException() {
        super("Indicators are not disjunct");
    }
}
