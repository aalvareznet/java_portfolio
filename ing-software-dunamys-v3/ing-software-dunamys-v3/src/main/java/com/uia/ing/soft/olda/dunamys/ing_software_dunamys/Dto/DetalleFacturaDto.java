package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Factura;

import lombok.Data;

@Data
public class DetalleFacturaDto {
    Integer id;

    Factura factura;

    String descripcionItem;

    Integer cantidad;

    Double precioUnitario;

}
