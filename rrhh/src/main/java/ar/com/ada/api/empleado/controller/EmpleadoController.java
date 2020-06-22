package ar.com.ada.api.empleado.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleado.entities.Empleado;
import ar.com.ada.api.empleado.models.requests.EmpleadoRequest;
import ar.com.ada.api.empleado.models.requests.SueldoInfoRequest;
import ar.com.ada.api.empleado.models.responses.GenericResponse;
import ar.com.ada.api.empleado.services.CategoriaService;
import ar.com.ada.api.empleado.services.EmpleadoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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
    public ResponseEntity<?> busarEmpleadoPorId(@PathVariable int id) {

        Empleado empleado;
        empleado = empleadoService.buscarEmpleadoPorId(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        // return ResponseEntity.notFound().build();//1na manera(builders,objeto q arma
        // otro objeto)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 2da manera(objeto)
    }

    @PutMapping("/empleados/{id}/sueldos")
    public ResponseEntity<?> actualizarSueldoEmpleado(@PathVariable int id, @RequestBody SueldoInfoRequest infoNueva) {

        Empleado empleadoOriginal = empleadoService.buscarEmpleadoPorId(id);

        if (empleadoOriginal != null) {
            empleadoService.actualizarSueldoEmpleado(empleadoOriginal, infoNueva.sueldoNuevo);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = empleadoOriginal.getEmpleadoId();
            resp.sueldo = empleadoOriginal.getSueldo();
            resp.message = "Sueldo actualizado, felicidades";

            return ResponseEntity.ok(resp);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "empleados/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable int id) {
        
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        
        if (empleado != null) {

            empleadoService.buscarEmpleadoPorId(id);

        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = empleado.getEmpleadoId();
        resp.message = "Empleado eliminado, felicidades";

        return ResponseEntity.ok(resp);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    
}