package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ClienteServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.EstadoFacturaServicio;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service.ReservacionServicio;

@Component
public class FacturaMapper {
    private final ModelMapper modelMapper;
    private final ReservacionServicio reservacionServicio;
    private final EstadoFacturaServicio estadoFacturaServicio;
    private final ClienteServicio clienteServicio;

    public FacturaMapper(ModelMapper modelMapper
                        , ReservacionServicio reservacionServicio
                        , EstadoFacturaServicio estadoFacturaServicio
                        , ClienteServicio clienteServicio) {
        this.reservacionServicio = reservacionServicio;
        this.estadoFacturaServicio = estadoFacturaServicio;
        this.clienteServicio = clienteServicio;
        this.modelMapper = modelMapper;
    }
    public Factura ConvertDTOToEntity(FacturaDto facturaDto) {
        Factura factura = modelMapper.map(facturaDto, Factura.class);
        Optional<Reservacion> reservacion = reservacionServicio.findById(facturaDto.getReservacionId());
        Optional<EstadoFactura> estadoFactura = estadoFacturaServicio.findById(facturaDto.getEstadoFacturaId());
        Optional<Cliente> cliente = clienteServicio.findById(facturaDto.getClienteId());
        factura.setReservacion(reservacion.get());
        factura.setEstadoFactura(estadoFactura.get());
        factura.setCliente(cliente.get());
        return factura;
    }
    public Factura ConvertDTOToEntity(FacturaCrearDto facturaDto) {
        Factura factura = modelMapper.map(facturaDto, Factura.class);
        Optional<Reservacion> reservacion = reservacionServicio.findById(facturaDto.getReservacionId());
        Optional<EstadoFactura> estadoFactura = estadoFacturaServicio.findById(facturaDto.getEstadoFacturaId());
        Optional<Cliente> cliente = clienteServicio.findById(facturaDto.getClienteId());
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
