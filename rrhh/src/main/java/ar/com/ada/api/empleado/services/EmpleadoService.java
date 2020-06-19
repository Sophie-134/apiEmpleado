package ar.com.ada.api.empleado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.entities.Empleado;
import ar.com.ada.api.empleado.repos.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepo;

    public void crearEmpleado(Empleado empleado) {
        empleadoRepo.save(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepo.findAll();
    }

    public Empleado buscarEmpleadoPorId(int empleadoId) {
         // return empleadoRepo.findById(empleadoId);
        Optional<Empleado> eo = empleadoRepo.findById(empleadoId);

        if (eo.isPresent()) {
            return eo.get();
        }
        return null;
    }

 
}