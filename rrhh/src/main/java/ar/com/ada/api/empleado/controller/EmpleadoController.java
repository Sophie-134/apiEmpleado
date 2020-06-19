package ar.com.ada.api.empleado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleado.entities.Empleado;
import ar.com.ada.api.empleado.services.EmpleadoService;

@RestController
public class EmpleadoController {
  
    @Autowired
    EmpleadoService empleadoService;


    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado){
         empleadoService.crearEmpleado(empleado);
         return ResponseEntity.ok(empleado.getEmpleadoId());
    }


    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleados(){
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    
}