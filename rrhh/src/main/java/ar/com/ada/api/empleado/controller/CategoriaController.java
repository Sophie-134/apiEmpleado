package ar.com.ada.api.empleado.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.empleado.entities.Categoria;
import ar.com.ada.api.empleado.models.requests.SueldoActualizadoRequest;
import ar.com.ada.api.empleado.models.responses.GenericResponse;
import ar.com.ada.api.empleado.services.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria) {
        categoriaService.crearCategoria(categoria);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @PutMapping("/categorias/{categoriaId}/sueldoBase")
    public ResponseEntity<?> actualizarCategoria(@PathVariable int categoriaId,
            @RequestBody SueldoActualizadoRequest sueldoBaseActualiz) {

        Categoria categoria = categoriaService.buscarEnCategoria(categoriaId);

        categoriaService.actualizarCategoria(categoria, sueldoBaseActualiz.sueldoPostAct);

        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = categoria.getCategoriaId();
        resp.sueldo = categoria.getSueldoBase();
        resp.message = "Sueldo de la categoria "+categoria.getCategoriaId()+ " actualizado, felicidades";

        return ResponseEntity.ok(resp);
    }

}