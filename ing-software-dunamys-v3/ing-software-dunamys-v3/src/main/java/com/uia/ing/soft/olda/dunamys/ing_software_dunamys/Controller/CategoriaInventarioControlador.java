package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.CategoriaInventarioCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.CategoriaInventarioDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.CategoriaInventarioServicio;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/categoria/inventario")
public class CategoriaInventarioControlador {

    @Autowired
    private CategoriaInventarioServicio servicio;

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<CategoriaInventarioDto> agregarCategoria(@Valid @RequestBody CategoriaInventarioCrearDto entidad
                                                                , @PathVariable Integer userId) {
        CategoriaInventarioDto categoriaAgregada = servicio.crear(entidad, userId);
        if(categoriaAgregada != null){
            return ResponseEntity.ok(categoriaAgregada);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{userId}/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> borrarCategoria(@PathVariable Integer userId
                                                , @PathVariable Integer categoryId){
        String mensaje = servicio.borrar(userId, categoryId);
        if(mensaje.equals("Categoria eliminada")){
            return ResponseEntity.ok(mensaje);
        }
        return ResponseEntity.badRequest().body(mensaje);
    }
}
