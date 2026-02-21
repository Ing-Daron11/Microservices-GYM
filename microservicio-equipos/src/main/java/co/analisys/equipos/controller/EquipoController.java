package co.analisys.equipos.controller;

import co.analisys.equipos.model.Equipo;
import co.analisys.equipos.service.EquipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping
    public Equipo crear(@RequestBody Equipo equipo) {
        return equipoService.registrar(equipo);
    }

    @GetMapping
    public List<Equipo> listar() {
        return equipoService.listar();
    }
}
