package co.analisys.clases.service;

import co.analisys.clases.exception.InvalidEntityException;
import co.analisys.clases.exception.ResourceNotFoundException;
import co.analisys.clases.exception.ServiceUnavailableException;
import co.analisys.clases.model.Clase;
import co.analisys.clases.model.ClaseId;
import co.analisys.clases.model.EntrenadorId;
import co.analisys.clases.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${entrenador.service.url}")
    private String entrenadorBaseUrl;

    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    public Clase programar(Clase clase) {
        // Validar que no exista una clase con el mismo ID
        if (clase.getId() != null && claseRepository.existsById(clase.getId())) {
            throw new InvalidEntityException("El ID de clase ya existe: " + clase.getId().getClase_id());
        }

        if (clase.getEntrenadorId() != null && clase.getEntrenadorId().getEntrenador_id() != null) {
            validarEntrenador(clase.getEntrenadorId().getEntrenador_id());
        }
        return claseRepository.save(clase);
    }

    public List<Clase> listar() {
        return claseRepository.findAll();
    }

    public Clase asignarEntrenador(String claseId, String entrenadorIdValue) {
        Clase clase = claseRepository.findById(new ClaseId(claseId))
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada: " + claseId));
        validarEntrenador(entrenadorIdValue);
        clase.setEntrenadorId(new EntrenadorId(entrenadorIdValue));
        return claseRepository.save(clase);
    }

    private void validarEntrenador(String entrenadorId) {
        Boolean existe;
        try {
            @SuppressWarnings("unchecked")
            Map<String, Boolean> response = restTemplate.getForObject(
                    entrenadorBaseUrl + "/" + entrenadorId + "/existe",
                    Map.class);
            existe = response != null && response.getOrDefault("existe", false);
        } catch (RestClientException ex) {
            throw new ServiceUnavailableException(
                    "No se pudo conectar al servicio de entrenadores: " + ex.getMessage());
        }
        if (!existe) {
            throw new ResourceNotFoundException("Entrenador no encontrado: " + entrenadorId);
        }
    }
}
