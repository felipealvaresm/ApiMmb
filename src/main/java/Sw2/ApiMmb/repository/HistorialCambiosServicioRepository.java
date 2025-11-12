package Sw2.ApiMmb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Sw2.ApiMmb.Models.HistorialCambioServicio;

@Repository
public interface HistorialCambiosServicioRepository extends JpaRepository<HistorialCambioServicio, Long> {
    
}