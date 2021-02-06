package sbz.cardiagnosticbe.model.db;

import javax.persistence.*;

@Entity
public class RepairStep {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "failure_id")
    private Failure failure;

    private int orderNumber;

    @Column(length = 450)
    private String description;

    public RepairStep(Long id, Failure failure, int orderNumber, String description) {
        this.id = id;
        this.failure = failure;
        this.orderNumber = orderNumber;
        this.description = description;
    }

    public RepairStep() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
