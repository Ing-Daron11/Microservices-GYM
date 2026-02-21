package co.analisys.miembros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Miembro {
    @EmbeddedId
    private MiembroId id;

    private String nombre;

    @Embedded
    private Email email;

    private LocalDate fechaInscripcion;
}
