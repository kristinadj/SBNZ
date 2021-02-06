package sbz.cardiagnosticbe.model.db;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class RelatedFailures {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private Set<Failure> failures = new HashSet<>();

    @Column(length = 450)
    private String repairDescription;

    public RelatedFailures(Long id, Set<Failure> failures, String repairDescription) {
        this.id = id;
        this.failures = failures;
        this.repairDescription = repairDescription;
    }

    public RelatedFailures() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Failure> getFailures() {
        return failures;
    }

    public void setFailures(Set<Failure> failures) {
        this.failures = failures;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelatedFailures that = (RelatedFailures) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
