package co.analisys.miembros.controller;

import co.analisys.miembros.model.Miembro;
import co.analisys.miembros.service.MiembroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @PostMapping
    public Miembro crear(@RequestBody Miembro miembro) {
        return miembroService.registrar(miembro);
    }

    @GetMapping
    public List<Miembro> listar() {
        return miembroService.listar();
    }
}
