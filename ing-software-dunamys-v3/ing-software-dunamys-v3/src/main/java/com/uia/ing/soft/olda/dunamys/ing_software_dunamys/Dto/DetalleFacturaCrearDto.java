package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DetalleFacturaCrearDto {
    @NotBlank(message = "El id de la factura no puede estar vacío")
    Integer facturaId;
    @NotBlank(message = "La descripcion del producto no puede estar vacío")
    String descripcionItem;
    @NotBlank(message = "La cantidad del producto no puede estar vacío")
    Integer cantidad;
    @NotBlank(message = "El precio unitario del producto no puede estar vacío")
    Double precioUnitario;
}
