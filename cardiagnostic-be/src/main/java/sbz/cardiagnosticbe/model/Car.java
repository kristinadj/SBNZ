package sbz.cardiagnosticbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String manufacturer;

    private String model;

    private String engine;

    private int year;
}
