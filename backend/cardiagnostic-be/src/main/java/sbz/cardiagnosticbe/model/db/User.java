package sbz.cardiagnosticbe.model.db;

import sbz.cardiagnosticbe.model.enums.Authority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Set<DetectedFailure> detectedFailures;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Set<DetectedRelatedFailures> detectedRelatedFailures;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User(Long id, String username, String password, String name, String lastName, Set<DetectedFailure> detectedFailures, Set<DetectedRelatedFailures> detectedRelatedFailures, Authority authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.detectedFailures = detectedFailures;
        this.detectedRelatedFailures = detectedRelatedFailures;
        this.authority = authority;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<DetectedFailure> getDetectedFailures() {
        return detectedFailures;
    }

    public void setDetectedFailures(Set<DetectedFailure> detectedFailures) {
        this.detectedFailures = detectedFailures;
    }

    public Set<DetectedRelatedFailures> getDetectedRelatedFailures() {
        return detectedRelatedFailures;
    }

    public void setDetectedRelatedFailures(Set<DetectedRelatedFailures> detectedRelatedFailures) {
        this.detectedRelatedFailures = detectedRelatedFailures;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}

