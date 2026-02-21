package co.analisys.clases.model;

import jakarta.persistence.*;
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

    private String nombre;

    @Embedded
    private Horario horario;

    private int capacidadMaxima;

    @Embedded
    private EntrenadorId entrenadorId; // referencia al microservicio de entrenadores
}
