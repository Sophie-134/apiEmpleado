package ar.com.ada.api.empleado.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.empleado.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
   
    

    
}