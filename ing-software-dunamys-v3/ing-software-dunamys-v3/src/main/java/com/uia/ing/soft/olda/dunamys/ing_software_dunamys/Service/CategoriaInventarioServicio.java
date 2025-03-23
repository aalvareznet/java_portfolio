package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.CategoriaInventarioCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.CategoriaInventarioDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.CategoriaInventarioMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.CategoriaInventario;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.CategoriaInventarioRepositorio;

@Service
public class CategoriaInventarioServicio extends BaseService<CategoriaInventario, Integer>{

    @Autowired
    private CategoriaInventarioRepositorio repo;
    @Autowired
    private LogAuditoriaServicio auditoria;
    @Autowired
    private CategoriaInventarioMapper mapper;

    protected CategoriaInventarioRepositorio getRepository() {
       return repo;
    }

    public CategoriaInventarioDto crear(CategoriaInventarioCrearDto entidad, Integer usuarioId){
        CategoriaInventario categoriaAgregada = this.create(mapper.ConvertCreateDTOToEntity(entidad));
        if(categoriaAgregada != null){
            auditoria.guardarAccion(usuarioId, "Crear nueva linea", "categoria_inventario");
            CategoriaInventarioDto categoriaDto = mapper.ConvertEntityToDTO(categoriaAgregada);
            return categoriaDto;
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
