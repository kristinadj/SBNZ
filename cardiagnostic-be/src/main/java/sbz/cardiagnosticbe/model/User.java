package sbz.cardiagnosticbe.model;

import sbz.cardiagnosticbe.model.enums.Authority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Set<DetectedFailure> detectedFailures;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<DetectedFailure> getDetectedFailures() {
        return detectedFailures;
    }

    public void setDetectedFailures(Set<DetectedFailure> detectedFailures) {
        this.detectedFailures = detectedFailures;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
