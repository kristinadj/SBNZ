package sbz.cardiagnosticbe.dto.detectedFailure;

import sbz.cardiagnosticbe.model.db.RepairStep;

public class TRepairStep implements Comparable<TRepairStep> {

    private Long id;
    private int orderNumber;
    private String description;

    public TRepairStep() {}

    public TRepairStep(Long id, int orderNumber, String description) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.description = description;
    }

    public TRepairStep(RepairStep repairStep) {
        this.id = repairStep.getId();
        this.orderNumber = repairStep.getOrderNumber();
        this.description = repairStep.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public int compareTo(TRepairStep o) {
        return Integer.compare(this.orderNumber, o.orderNumber);
    }
}
