package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.DetalleFacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.DetalleFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.FacturaServicio;

@Component
public class DetalleFacturaMapper {
    private final ModelMapper modelMapper;
    private final FacturaServicio facturaServicio;

    public DetalleFacturaMapper(ModelMapper modelMapper
                                , FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
        this.modelMapper = modelMapper;
    }

    public DetalleFacturaDto ConvertEntityToDTO(DetalleFactura detalleFactura) {
        DetalleFacturaDto detalleFacturaDto = modelMapper.map(detalleFactura, DetalleFacturaDto.class);
        detalleFacturaDto.setFacturaId(detalleFactura.getFactura().getId());
        return detalleFacturaDto;
    }

    public DetalleFactura ConvertDTOToEntity(DetalleFacturaDto detalleFacturaDto) {
        DetalleFactura detalleFactura = modelMapper.map(detalleFacturaDto, DetalleFactura.class);
        Optional<Factura> factura = facturaServicio.findById(detalleFacturaDto.getFacturaId());
        detalleFactura.setFactura(factura.get());
        return detalleFactura;
    }
    
}
