package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FacturaCrearDto {
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "La fecha no puede estar vacía")
    LocalDateTime fecha;
    @Digits(integer = 10, fraction = 0, message = "El id del cliente no puede ser un número entero")
    @NotBlank(message = "El id del cliente no puede estar vacío")
    Integer reservacionId;
    @Digits(integer = 10, fraction = 0, message = "El id del estado de la factura no puede ser un número entero")
    @NotBlank(message = "El id del estado de la factura no puede estar vacío")
    Integer estadoFacturaId;
    @Digits(integer = 10, fraction = 0, message = "El id del cliente no puede ser un número entero")
    @NotBlank(message = "El id del cliente no puede estar vacío")
    Integer clienteId;
}
