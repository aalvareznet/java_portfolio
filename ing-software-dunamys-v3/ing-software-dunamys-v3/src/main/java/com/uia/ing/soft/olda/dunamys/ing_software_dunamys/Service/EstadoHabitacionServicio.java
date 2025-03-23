package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoHabitacionCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.EstadoHabitacionDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.EstadoHabitacionMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoHabitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoHabitacionRepositorio;

@Service
public class EstadoHabitacionServicio extends BaseService<EstadoHabitacion, Integer>{

    @Autowired
    private EstadoHabitacionRepositorio repo;
    @Autowired
    private EstadoHabitacionMapper mapper;
    @Autowired
    private LogAuditoriaServicio auditoria;

    protected EstadoHabitacionRepositorio getRepository() {
        return repo;
    }

    public EstadoHabitacionDto crear(Integer usuarioId, EstadoHabitacionCrearDto estadoHabitacionDto){
        EstadoHabitacion estadoHabitacion = mapper.ConvertDTOToEntity(estadoHabitacionDto);
        EstadoHabitacion estadoHabitacionCreado = this.create(estadoHabitacion);
        if (estadoHabitacionCreado != null) {
            auditoria.guardarAccion(usuarioId, "Crear nuevo estado de habitacion", "estadoHabitacion");
            return mapper.ConvertEntityToDTO(estadoHabitacionCreado);
        }
        return null;
    }

    public String borrar(Integer id, Integer userId){
        Optional<EstadoHabitacion> estadoHabitacion = this.findById(id);
        if (estadoHabitacion.isPresent()) {
            this.delete(id);
            auditoria.guardarAccion(userId, "Eliminar estado de habitacion", "estadoHabitacion");
            return "Estado de habitacion eliminado";
        }
        return null;
    }

}
