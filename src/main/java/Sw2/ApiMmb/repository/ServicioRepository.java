package Sw2.ApiMmb.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import Sw2.ApiMmb.Models.Servicio;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Integer> {


}
