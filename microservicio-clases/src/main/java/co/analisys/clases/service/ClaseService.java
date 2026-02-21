package co.analisys.clases.service;

import co.analisys.clases.model.Clase;
import co.analisys.clases.repository.ClaseRepository;
import co.analisys.clases.model.ClaseId;
import co.analisys.clases.model.EntrenadorId;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public Clase programar(Clase clase) {
        // si se asignó entrenador, validar su existencia
        if (clase.getEntrenadorId() != null && clase.getEntrenadorId().getEntrenador_id() != null) {
            String entrenadorIdValue = clase.getEntrenadorId().getEntrenador_id();
            Boolean existeEntrenador = restTemplate.getForObject(
                    "http://localhost:8083/entrenadores/" + entrenadorIdValue + "/existe",
                    Boolean.class);
            if (existeEntrenador == null || !existeEntrenador) {
                throw new RuntimeException("El entrenador asignado no existe: " + entrenadorIdValue);
            }
        }
        return claseRepository.save(clase);
    }

    public List<Clase> listar() {
        return claseRepository.findAll();
    }

    public Clase asignarEntrenador(String claseId, String entrenadorIdValue) {
        Clase clase = claseRepository.findById(new ClaseId(claseId))
                .orElseThrow(() -> new RuntimeException("Clase no encontrada: " + claseId));
        // validar existencia del entrenador como antes
        Boolean existeEntrenador = restTemplate.getForObject(
                "http://localhost:8083/entrenadores/" + entrenadorIdValue + "/existe",
                Boolean.class);
        if (existeEntrenador == null || !existeEntrenador) {
            throw new RuntimeException("El entrenador asignado no existe: " + entrenadorIdValue);
        }
        clase.setEntrenadorId(new EntrenadorId(entrenadorIdValue));
        return claseRepository.save(clase);
    }
}
