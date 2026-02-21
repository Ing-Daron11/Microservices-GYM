package co.analisys.entrenadores.service;

import co.analisys.entrenadores.model.Entrenador;
import co.analisys.entrenadores.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public Entrenador registrar(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Boolean existePorId(String id) {
        return entrenadorRepository.existsById(new EntrenadorId(id));
    }

    public List<Entrenador> listar() {
        return entrenadorRepository.findAll();
    }
}
