package ar.com.ada.api.empleado.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.entities.Empleado;
import ar.com.ada.api.empleado.models.requests.SueldoInfoRequest;
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

    public void actualizarSueldoEmpleado(Empleado empleadoOriginal, BigDecimal sueldoActualizado) {
        empleadoOriginal.setSueldo(sueldoActualizado);
        empleadoRepo.save(empleadoOriginal);
    }

    public Object actualizarSueldoEmpleado() {
        return null;
    }

    // este actualiza para varios estados, no solo para dar de baja
    public void actualizarEstadoEmpleado(Empleado empleado, int estadoId) {
        empleado.setEstadoId(estadoId);
        empleadoRepo.save(empleado);
    }

    public void borrarEmpleado(Empleado empleado) {
        this.actualizarEstadoEmpleado(empleado, 0);
    }

}