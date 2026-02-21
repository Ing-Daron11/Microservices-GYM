package co.analisys.entrenadores.service;

import co.analisys.entrenadores.model.Entrenador;
import co.analisys.entrenadores.model.EntrenadorId;
import co.analisys.entrenadores.repository.EntrenadorRepository;
import co.analisys.entrenadores.exception.InvalidEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public Entrenador registrar(Entrenador entrenador) {
        // Validar que no exista un entrenador con el mismo ID
        if (entrenador.getId() != null && entrenadorRepository.existsById(entrenador.getId())) {
            throw new InvalidEntityException("El ID de entrenador ya existe: " + entrenador.getId().getEntrenador_id());
        }
        return entrenadorRepository.save(entrenador);
    }

    public Boolean existePorId(String id) {
        return entrenadorRepository.existsById(new EntrenadorId(id));
    }

    public List<Entrenador> listar() {
        return entrenadorRepository.findAll();
    }
}
