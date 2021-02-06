package sbz.cardiagnosticbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RelatedFailureException extends RuntimeException {

    public RelatedFailureException(String msg) {
        super(msg);
    }
}
