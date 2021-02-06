package sbz.cardiagnosticbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VehicleModelException extends RuntimeException {

    public VehicleModelException(String msg) {
        super(msg);
    }
}
