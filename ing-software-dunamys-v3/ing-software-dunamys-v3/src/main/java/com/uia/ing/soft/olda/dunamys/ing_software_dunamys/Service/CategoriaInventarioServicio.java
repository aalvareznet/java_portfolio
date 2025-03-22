package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.CategoriaInventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.CategoriaInventarioRepositorio;

@Service
public class CategoriaInventarioServicio extends BaseService<CategoriaInventario, Integer>{

    @Autowired
    private CategoriaInventarioRepositorio repo;
    @Autowired
    private LogAuditoriaServicio auditoria;

    protected CategoriaInventarioRepositorio getRepository() {
       return repo;
    }

    public CategoriaInventario crear(CategoriaInventario entidad, Integer usuarioId){
        CategoriaInventario categoriaAgregada = this.create(entidad);
        if(categoriaAgregada != null){
            auditoria.guardarAccion(usuarioId, "Crear nueva linea", "categoria_inventario");
            return categoriaAgregada;
        }
        return null;
    }

    public String borrar(Integer usuarioId, Integer categoriaId){
        Optional<CategoriaInventario> categoria = this.findById(categoriaId);
        if(categoria.isPresent()){
            auditoria.guardarAccion(usuarioId, "Categoria borrada", "categoria_inventario");
            this.delete(categoriaId);
            return "Categoria eliminada";
        }
        return "Categoria no encontrada";
    }
}
