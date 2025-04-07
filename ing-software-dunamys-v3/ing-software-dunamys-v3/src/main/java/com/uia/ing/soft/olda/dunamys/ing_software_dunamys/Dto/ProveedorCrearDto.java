package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProveedorCrearDto {
    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    String nombre;
    @NotBlank(message = "El correo del proveedor no puede estar vacío")
    String correoReferencia;
    @NotBlank(message = "El teléfono del proveedor no puede estar vacío")
    String telefono;  
}
