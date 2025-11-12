package Sw2.ApiMmb.services;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Sw2.ApiMmb.Models.HistorialCambioServicio;
import Sw2.ApiMmb.repository.HistorialCambiosServicioRepository;

@Service
public class HistorialCambioServicioService {

    @Autowired
    private HistorialCambiosServicioRepository repository;

    public ArrayList<HistorialCambioServicio> listarTodo() {
        System.out.println("Entrando a listarTodo()");
        ArrayList<HistorialCambioServicio> lista = new ArrayList<>(repository.findAll());
        System.out.println("Total registros: " + lista.size());
        return lista;
    }

}
