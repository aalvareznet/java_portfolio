package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import lombok.Data;

@Data
public class InventarioCrearDto {
    String nombreItem;

    Integer stock;

    Double precio;

    Integer categoriaInventarioId;

    Integer proveedorId;
}
