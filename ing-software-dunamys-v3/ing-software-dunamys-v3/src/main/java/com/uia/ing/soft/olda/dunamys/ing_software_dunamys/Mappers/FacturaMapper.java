package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ClienteRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.EstadoFacturaRepositorio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ReservacionRepositorio;

@Component
public class FacturaMapper {
    private final ModelMapper modelMapper;
    private final ReservacionRepositorio reservacionRepositorio;
    private final EstadoFacturaRepositorio estadoFacturaRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public FacturaMapper(ModelMapper modelMapper,
                         ReservacionRepositorio reservacionRepositorio,
                         EstadoFacturaRepositorio estadoFacturaRepositorio,
                         ClienteRepositorio clienteRepositorio) {
        this.reservacionRepositorio = reservacionRepositorio;
        this.estadoFacturaRepositorio = estadoFacturaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.modelMapper = modelMapper;
        configureModelMapper();
    }

    private void configureModelMapper() {
        modelMapper.addMappings(new PropertyMap<FacturaCrearDto, Factura>() {
            @Override
            protected void configure() {
                skip(destination.getId()); // Skip the id property to avoid conflicts
            }
        });
    }

    public Factura ConvertDTOToEntity(FacturaDto facturaDto) {
        Factura factura = modelMapper.map(facturaDto, Factura.class);
        Optional<Reservacion> reservacion = reservacionRepositorio.findById(facturaDto.getReservacionId());
        Optional<EstadoFactura> estadoFactura = estadoFacturaRepositorio.findById(facturaDto.getEstadoFacturaId());
        Optional<Cliente> cliente = clienteRepositorio.findById(facturaDto.getClienteId());
        factura.setReservacion(reservacion.get());
        factura.setEstadoFactura(estadoFactura.get());
        factura.setCliente(cliente.get());
        return factura;
    }

    public Factura ConvertCreateDTOToEntity(FacturaCrearDto facturaDto) {
        Factura factura = modelMapper.map(facturaDto, Factura.class);
        Optional<Reservacion> reservacion = reservacionRepositorio.findById(facturaDto.getReservacionId());
        Optional<EstadoFactura> estadoFactura = estadoFacturaRepositorio.findById(facturaDto.getEstadoFacturaId());
        Optional<Cliente> cliente = clienteRepositorio.findById(facturaDto.getClienteId());
        factura.setReservacion(reservacion.get());
        factura.setEstadoFactura(estadoFactura.get());
        factura.setCliente(cliente.get());
        return factura;
    }

    public FacturaDto ConvertEntityToDTO(Factura factura) {
        FacturaDto facturaDto = modelMapper.map(factura, FacturaDto.class);
        facturaDto.setReservacionId(factura.getReservacion().getId());
        facturaDto.setEstadoFacturaId(factura.getEstadoFactura().getId());
        facturaDto.setClienteId(factura.getCliente().getId());
        return facturaDto;
    }
}
