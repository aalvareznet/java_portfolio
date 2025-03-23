package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.DetalleFacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaCrearDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto.FacturaDto;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Mappers.FacturaMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.EstadoFactura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.FacturaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class FacturaServicio extends BaseService<Factura, Integer>{

    @Autowired
    private FacturaRepositorio repo;
    @Autowired
    private FacturaMapper mapper;
    @Autowired
    private LogAuditoriaServicio auditoria;
    @Autowired
    private DetalleFacturaServicio detalleFacturaServicio;
    @Autowired
    private EstadoFacturaServicio estadoFacturaServicio;

    protected FacturaRepositorio getRepository() {
        return repo;
    }
    public FacturaDto crear(Integer usuarioId, FacturaCrearDto facturaDto){
        Factura factura = mapper.ConvertDTOToEntity(facturaDto);
        Factura nuevaFactura = create(factura);
        if (nuevaFactura != null) {
            auditoria.guardarAccion(usuarioId, "Crear nueva factura con el ID " + nuevaFactura.getId(), "factura");
            return mapper.ConvertEntityToDTO(nuevaFactura);
        }
        return null;
    }
    @Transactional
    public FacturaDto agregarDetalleFactura(Integer facturaId, Integer usuarioId, List<DetalleFacturaCrearDto> listaDetallesFacturaDto){
        Optional<Factura> factura = this.findById(facturaId);
        if(factura.isPresent() && !listaDetallesFacturaDto.isEmpty()){
            detalleFacturaServicio.agregarDetalleFactura(listaDetallesFacturaDto);
            auditoria.guardarAccion(usuarioId, "Agregar detalle a la factura numero " + factura.get().getId(), "factura");
            return mapper.ConvertEntityToDTO(factura.get());
        }
        return null;
    }
    public FacturaDto pagarFactura(Integer facturaId, Integer userId){
        Optional<Factura> factura = this.findById(userId);
        if(factura.isPresent()){
            Optional<EstadoFactura> estadoFactura = estadoFacturaServicio.findById(1); //colocar cual es el ID de la factura pagada
            Factura facturaParaActualizar = factura.get();
            facturaParaActualizar.setEstadoFactura(estadoFactura.get());
            Factura facturaActualizada = this.update(facturaParaActualizar);
            auditoria.guardarAccion(userId, "Pagar factura con el ID " + facturaActualizada.getId(), "factura");
            return mapper.ConvertEntityToDTO(facturaActualizada);
        }
        return null;
    }
}
