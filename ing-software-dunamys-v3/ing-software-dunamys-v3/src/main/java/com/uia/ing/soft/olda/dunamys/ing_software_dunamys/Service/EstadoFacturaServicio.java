package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoFacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoFacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.EstadoFacturaMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoFacturaRepositorio;

@Service
public class EstadoFacturaServicio extends BaseService<EstadoFactura, Integer>{

    @Autowired
    private EstadoFacturaRepositorio repo;
    @Autowired
    private EstadoFacturaMapper mapper;
    @Autowired
    private LogAuditoriaServicio auditoria;

    protected EstadoFacturaRepositorio getRepository() {
        return repo;    
    }

    public EstadoFacturaDto agregar(Integer usuarioId, EstadoFacturaCrearDto estadoFacturaDto){
        EstadoFactura estadoFactura = mapper.ConvertCreateDTOToEntity(estadoFacturaDto);
        EstadoFactura estadoFacturaResultante = this.create(estadoFactura);
        if (estadoFacturaResultante != null) {
            auditoria.guardarAccion(usuarioId, "Crear nuevo estado de factura", "estadoFactura");
            return mapper.ConvertEntityToDTO(estadoFacturaResultante);
        }
        return null;
    }
    
    public String eliminar(Integer usuarioId, Integer id){
        Optional<EstadoFactura> estadoFactura = findById(id);
        if(estadoFactura.isPresent()){
            this.delete(id);
            auditoria.guardarAccion(usuarioId, "Eliminar estado de factura", "estadoFactura");
            return "Estado de factura eliminado";
        }
        return null;
    }
}
