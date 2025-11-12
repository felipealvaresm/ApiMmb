package Sw2.ApiMmb.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Sw2.ApiMmb.Models.Servicio;
import Sw2.ApiMmb.services.ServicioService;

@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = "*")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listar")
    public ArrayList<Servicio> listar() {
        return servicioService.GetAll();
    }

    @PostMapping("/guardar")
    public Servicio guardarServicio(@org.springframework.web.bind.annotation.RequestBody Servicio servicio) {
        return servicioService.save(servicio);
    }

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable int id) {
    boolean eliminado = servicioService.eliminarServicio(id);
    if (eliminado) {
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("No se pudo eliminar el servicio con ID " + id);
    }
}

    @PutMapping("/{idServicio}")
    public ResponseEntity<Servicio> actualizarServicio(
            @PathVariable int idServicio,
            @org.springframework.web.bind.annotation.RequestBody Servicio servicio) {

        try {
            Servicio actualizado = servicioService.actualizarServicio(idServicio, servicio);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}