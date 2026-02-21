package co.analisys.clases.controller;

import co.analisys.clases.model.Clase;
import co.analisys.clases.service.ClaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @PostMapping
    public Clase programar(@Valid @RequestBody Clase clase) {
        return claseService.programar(clase);
    }

    @PutMapping("/{id}/entrenador/{entrenadorId}")
    public Clase asignarEntrenador(@PathVariable String id, @PathVariable String entrenadorId) {
        return claseService.asignarEntrenador(id, entrenadorId);
    }

    @GetMapping
    public List<Clase> listar() {
        return claseService.listar();
    }
}
