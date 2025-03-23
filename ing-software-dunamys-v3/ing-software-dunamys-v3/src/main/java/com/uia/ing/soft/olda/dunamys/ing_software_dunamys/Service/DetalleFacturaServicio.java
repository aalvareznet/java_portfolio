package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.DetalleFacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.DetalleFacturaMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.DetalleFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.DetalleFacturaRepositorio;

@Service
public class DetalleFacturaServicio extends BaseService<DetalleFactura, Integer>{

    @Autowired
    private DetalleFacturaRepositorio repo;
    @Autowired
    private DetalleFacturaMapper mapper;

    protected DetalleFacturaRepositorio getRepository() {
        return repo;    
    }
    public void agregarDetalleFactura(List<DetalleFacturaCrearDto> listaDetalleFactura){
        List<DetalleFactura> listaDetalleFacturaEntidad = listaDetalleFactura.stream()
                                                                            .map(detalleFactura -> mapper.ConvertDTOToEntity(detalleFactura))
                                                                            .collect(Collectors.toList());
        for(DetalleFactura detalle : listaDetalleFacturaEntidad){
            this.create(detalle);
        }
    }
}
