package sbz.cardiagnosticbe.dto.indicator;

import sbz.cardiagnosticbe.model.db.Indicator;

public class TIndicator {
    private Long id;

    private String description;

    public TIndicator() { }

    public TIndicator(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public TIndicator(Indicator indicator) {
        this.id = indicator.getId();
        this.description = indicator.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
