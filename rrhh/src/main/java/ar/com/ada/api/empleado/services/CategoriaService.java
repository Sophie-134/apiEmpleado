package ar.com.ada.api.empleado.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.entities.Categoria;
import ar.com.ada.api.empleado.entities.Empleado;
import ar.com.ada.api.empleado.repos.CategoriaRepository;


@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepo;

    public void crearCategoria(Categoria categoria) {
        categoriaRepo.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    public List<Empleado> listarCategEmpleadoPorId(int categoriaId) {
        Optional<Categoria> catId = categoriaRepo.findById(categoriaId);
        List<Empleado> listaVacia = new ArrayList<>();

        if (catId.isPresent()) {
            return (catId.get()).getEmpleados();
        }
        return listaVacia;
    }

}