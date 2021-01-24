package sbz.cardiagnosticbe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sbz.cardiagnosticbe.model.enums.Authority;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TSignInResponse {

    private String username;

    private Authority role;

    private String token;
}
