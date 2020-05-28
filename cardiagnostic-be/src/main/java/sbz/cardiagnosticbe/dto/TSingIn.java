package sbz.cardiagnosticbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TSingIn {
    private String username;
    private String password;
}
