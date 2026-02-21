package co.analisys.entrenadores.controller;

import co.analisys.entrenadores.model.Entrenador;
import co.analisys.entrenadores.service.EntrenadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public Entrenador crear(@RequestBody Entrenador entrenador) {
        return entrenadorService.registrar(entrenador);
    }

    @GetMapping("/{id}/existe")
    public Boolean existeEntrenador(@PathVariable String id) {
        return entrenadorService.existePorId(id);
    }

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorService.listar();
    }
}
