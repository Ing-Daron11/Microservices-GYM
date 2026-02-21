package co.analisys.equipos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    @EmbeddedId
    private EquipoId id;

    private String nombre;

    @Embedded
    private Descripcion descripcion;

    private int cantidad;
}
