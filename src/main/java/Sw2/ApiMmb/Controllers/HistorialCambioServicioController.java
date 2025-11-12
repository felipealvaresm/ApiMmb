package Sw2.ApiMmb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Sw2.ApiMmb.Models.HistorialCambioServicio;
import Sw2.ApiMmb.services.HistorialCambioServicioService;

@RestController
@RequestMapping("/historial")
@CrossOrigin(origins = "*")
public class HistorialCambioServicioController {

    @Autowired
    private HistorialCambioServicioService service;

    @GetMapping
    public ResponseEntity<List<HistorialCambioServicio>> obtenerHistorial() {
        return ResponseEntity.ok(service.listarTodo());
    }
}

