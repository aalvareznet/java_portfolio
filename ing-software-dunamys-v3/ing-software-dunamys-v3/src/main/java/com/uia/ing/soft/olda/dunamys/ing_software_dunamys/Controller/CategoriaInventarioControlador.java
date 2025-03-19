package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.CategoriaInventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.CategoriaInventarioServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.LogAuditoriaServicio;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/categoria/inventario")
public class CategoriaInventarioControlador {

    @Autowired
    private CategoriaInventarioServicio servicio;
    @Autowired
    private LogAuditoriaServicio auditoria;

    @PostMapping("/{userId}")
    public ResponseEntity<CategoriaInventario> agregarCategoria(@RequestBody CategoriaInventario entidad
                                                                , @PathVariable Integer userId) {
        CategoriaInventario categoriaResultante = servicio.create(entidad);
        if (categoriaResultante != null) {
            auditoria.guardarAccion(userId, "Crear nueva linea", "categoria_inventario");
            return ResponseEntity.ok(categoriaResultante);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{userId}/{categoryId}")
    public ResponseEntity<String> borrarCategoria(@PathVariable Integer userId
                                                , @PathVariable Long categoryId){
        Optional<CategoriaInventario> categoria = servicio.findById(categoryId);
        if(categoria.isPresent()){
            auditoria.guardarAccion(userId, "Categoria borrada", "categoria_inventario");
            servicio.delete(categoryId);
            return ResponseEntity.ok("Categoria eliminada");
        }
        return ResponseEntity.notFound().build();
    }
}
