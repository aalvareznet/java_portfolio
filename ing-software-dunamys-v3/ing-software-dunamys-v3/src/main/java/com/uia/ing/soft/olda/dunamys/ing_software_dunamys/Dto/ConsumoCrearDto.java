package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class ConsumoCrearDto {
    @NotBlank(message = "El id del inventario no puede estar vacío")
    @Digits(integer = 10, fraction = 0, message = "El id del inventario debe ser un número entero")
    Integer inventarioId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "La fecha no puede estar vacía")
    LocalDateTime fecha;
    @NotBlank(message = "El id del cliente no puede estar vacío")
    @Digits(integer = 10, fraction = 0, message = "El id del cliente debe ser un número entero")
    Integer clienteId;
    @NotBlank(message = "La cantidad del producto no puede estar vacío")
    Integer cantidad;

}
