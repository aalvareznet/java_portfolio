package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;


import lombok.Data;

@Data
public class DetalleFacturaDto {
    Integer id;

    Integer facturaId;

    String descripcionItem;

    Integer cantidad;

    Double precioUnitario;

}
