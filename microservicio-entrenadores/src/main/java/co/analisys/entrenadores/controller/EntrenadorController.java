package co.analisys.entrenadores.controller;

import co.analisys.entrenadores.model.Entrenador;
import co.analisys.entrenadores.service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public Entrenador crear(@Valid @RequestBody Entrenador entrenador) {
        return entrenadorService.registrar(entrenador);
    }

    @GetMapping("/{id}/existe")
    public Map<String, Boolean> existeEntrenador(@PathVariable String id) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("existe", entrenadorService.existePorId(id));
        return response;
    }

    @GetMapping
    public List<Entrenador> listar() {
        return entrenadorService.listar();
    }
}
