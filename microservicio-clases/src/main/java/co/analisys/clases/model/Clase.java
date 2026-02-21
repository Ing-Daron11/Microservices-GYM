package co.analisys.clases.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clase {
    @EmbeddedId
    private ClaseId id;

    @NotBlank(message = "El nombre de la clase no puede estar vacío")
    private String nombre;

    @Embedded
    private Horario horario;

    @Positive(message = "La capacidad máxima debe ser mayor a 0")
    private int capacidadMaxima;

    @Embedded
    private EntrenadorId entrenadorId; // referencia al microservicio de entrenadores
}
