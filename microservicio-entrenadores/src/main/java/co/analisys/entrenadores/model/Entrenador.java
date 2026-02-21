package co.analisys.entrenadores.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador {
    @EmbeddedId
    private EntrenadorId id;

    private String nombre;

    @Embedded
    private Especialidad especialidad;
}
