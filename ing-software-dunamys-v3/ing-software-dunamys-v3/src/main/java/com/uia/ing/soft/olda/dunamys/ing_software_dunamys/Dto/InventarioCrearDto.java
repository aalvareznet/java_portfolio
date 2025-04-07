package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InventarioCrearDto {
    @NotBlank(message = "El nombre del item no puede estar vacío")
    String nombreItem;
    @Digits(integer = 10, fraction = 2, message = "El stock debe ser un número entero")
    @NotBlank(message = "El stock no puede estar vacío")
    Integer stock;
    @Digits(integer = 10, fraction = 2, message = "El precio debe ser un número decimal de hasta 10 dígitos y 2 decimales")
    @NotBlank(message = "El precio no puede estar vacío")
    Double precio;
    @Digits(integer = 10, fraction = 0, message = "El id del tipo de item debe ser un número entero")
    @NotBlank(message = "El id del tipo de item no puede estar vacío")
    Integer categoriaInventarioId;
    @Digits(integer = 10, fraction = 0, message = "El id del proveedor debe ser un número entero")
    @NotBlank(message = "El id del proveedor no puede estar vacío")
    Integer proveedorId;
}
