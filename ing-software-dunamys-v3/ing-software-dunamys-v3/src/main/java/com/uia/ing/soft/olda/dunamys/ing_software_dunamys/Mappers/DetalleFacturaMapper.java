package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.DetalleFacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.DetalleFacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.DetalleFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.FacturaRepositorio;

@Component
public class DetalleFacturaMapper {
    private final ModelMapper modelMapper;
    private final FacturaRepositorio facturaRepositorio;

    public DetalleFacturaMapper(ModelMapper modelMapper
                                , FacturaRepositorio facturaRepositorio) {
        this.facturaRepositorio = facturaRepositorio;
        this.modelMapper = modelMapper;
    }

    public DetalleFactura ConvertDTOToEntity(DetalleFacturaDto detalleFacturaDto) {
        DetalleFactura detalleFactura = modelMapper.map(detalleFacturaDto, DetalleFactura.class);
        Optional<Factura> factura = facturaRepositorio.findById(detalleFacturaDto.getFacturaId());
        detalleFactura.setFactura(factura.get());
        return detalleFactura;
    }

    public DetalleFactura ConvertCreateDTOToEntity(DetalleFacturaCrearDto detalleFacturaDto) {
        DetalleFactura detalleFactura = modelMapper.map(detalleFacturaDto, DetalleFactura.class);
        Optional<Factura> factura = facturaRepositorio.findById(detalleFacturaDto.getFacturaId());
        detalleFactura.setFactura(factura.get());
        return detalleFactura;
    }

    public DetalleFacturaDto ConvertEntityToDTO(DetalleFactura detalleFactura) {
        DetalleFacturaDto detalleFacturaDto = modelMapper.map(detalleFactura, DetalleFacturaDto.class);
        detalleFacturaDto.setFacturaId(detalleFactura.getFactura().getId());
        return detalleFacturaDto;
    }


    
}
