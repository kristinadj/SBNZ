package sbz.cardiagnosticbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetectedFailure {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ManyToOne
    private Failure failure;

    @ManyToOne
    private Car car;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private List<Date> diagnoseHistory;

    @Enumerated(EnumType.STRING)
    private FailureFrequency failureFrequency;

    private Boolean isReplaced;
}
