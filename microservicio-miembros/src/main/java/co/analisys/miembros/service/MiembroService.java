package co.analisys.miembros.service;

import co.analisys.miembros.model.Miembro;
import co.analisys.miembros.repository.MiembroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    public Miembro registrar(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    public List<Miembro> listar() {
        return miembroRepository.findAll();
    }
}
