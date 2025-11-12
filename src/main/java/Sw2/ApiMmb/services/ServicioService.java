package Sw2.ApiMmb.services;

import java.util.ArrayList;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Sw2.ApiMmb.Models.Servicio;
import Sw2.ApiMmb.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository _repo;

    public ArrayList<Servicio> GetAll() {
        ArrayList<Servicio> datos = (ArrayList<Servicio>) _repo.findAll();
        if (datos.isEmpty()) {
            // Se crea un registro de ejemplo si la base de datos está vacía
            Servicio a = new Servicio();
            a.setIdServicio(1);
            a.setNombreCliente("Cliente de ejemplo");
            a.setIdCliente("C001");
            a.setTipoServicio("Mantenimiento");
            a.setTelefono("3001234567");
            a.setFechaServicio("2025-10-12");
            a.setPrecio(50000);

            datos.add(a);
        }

        return datos;
    }

    public Servicio save(Servicio servicio) {
        return _repo.save(servicio);
    }

    public boolean eliminarServicio(int idServicio) {
    try {
        _repo.deleteById(idServicio);
        return true;
    } catch (Exception e) {
        return false;
    }
}

    // actualiza un servicio existente
    public Servicio actualizarServicio(int idServicio, Servicio nuevoServicio) {
        Optional<Servicio> existente = _repo.findById(idServicio);

        if (existente.isPresent()) {
            Servicio s = existente.get();

            s.setNombreCliente(nuevoServicio.getNombreCliente());
            s.setIdCliente(nuevoServicio.getIdCliente());
            s.setTipoServicio(nuevoServicio.getTipoServicio());
            s.setTelefono(nuevoServicio.getTelefono());
            s.setFechaServicio(nuevoServicio.getFechaServicio());
            s.setPrecio(nuevoServicio.getPrecio());

            return _repo.save(s); 
        } else {
            throw new RuntimeException("Servicio con id " + idServicio + " no encontrado");
        }
    }
}