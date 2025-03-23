package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoReservacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoReservacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.EstadoReservacionMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoReservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoReservacionRepositorio;

@Service
public class EstadoReservacionServicio extends BaseService<EstadoReservacion, Integer>{

    @Autowired
    private EstadoReservacionRepositorio repo;
    @Autowired
    private EstadoReservacionMapper mapper;
    @Autowired
    private LogAuditoriaServicio auditoria;
    protected EstadoReservacionRepositorio getRepository() {
        return repo;    
    }
    public EstadoReservacionDto crear(Integer usuarioId, EstadoReservacionCrearDto estadoREservacionDto){
        EstadoReservacion estadoReservacion = mapper.ConvertCreateDTOToEntity(estadoREservacionDto);
        EstadoReservacion reservacionCreada = this.create(estadoReservacion);
        if (reservacionCreada != null) {
            auditoria.guardarAccion(usuarioId, "Crear nuevo estado de reservacion", "estadoReservacion");
            return mapper.ConvertEntityToDTO(reservacionCreada);
        }
        return null;
    }

    public String borrar(Integer id, Integer usuarioId){
        Optional<EstadoReservacion> estadoReservacion = this.findById(id);
        if (estadoReservacion.isPresent()) {
            this.delete(id);
            auditoria.guardarAccion(usuarioId, "Eliminar estado de reservacion", "estadoReservacion");
            return "Estado de reservacion eliminado";
        }
        return null;
    }
}
