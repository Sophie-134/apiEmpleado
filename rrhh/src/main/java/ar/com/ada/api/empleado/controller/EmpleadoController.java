package ar.com.ada.api.empleado.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleado.entities.Empleado;
import ar.com.ada.api.empleado.models.requests.EmpleadoRequest;
import ar.com.ada.api.empleado.models.responses.GenericResponse;
import ar.com.ada.api.empleado.services.CategoriaService;
import ar.com.ada.api.empleado.services.EmpleadoService;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoRequest info) {
        Empleado empleado = new Empleado();
        empleado.setNombre(info.nombre);
        empleado.setEdad(info.edad);
        empleado.setSueldo(info.sueldo);
        empleado.setCategoria(categoriaService.buscarEnCategoria(info.categoriaId));
        empleado.setFechaAlta(new Date());
        empleado.setEstadoId(1);

        empleadoService.crearEmpleado(empleado);
        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = empleado.getEmpleadoId();
        resp.message = "Empleado creado, felicidades";
        return ResponseEntity.ok(resp);// --> devuelve un json
        // return ResponseEntity.ok(empleado.getEmpleadoId());-->este solo devuelve un
        // numero. Es mejor devolver un json
    }

    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleados() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> busarEmpleadoPorId(@PathVariable int id){
    
        Empleado empleado;
        empleado= empleadoService.buscarEmpleadoPorId(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        //return ResponseEntity.notFound().build();//1na manera(builders,objeto q arma otro objeto)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //2da manera(objeto)
    }

}