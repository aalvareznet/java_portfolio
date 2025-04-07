package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data   
@Valid
public class ReservacionCrearDto {
    @Digits(integer = 10, fraction = 0, message = "El id de la habitación no puede estar vacío")
    @NotBlank(message = "El id de la habitación no puede estar vacío")
    Integer habitacionId;
    @Digits(integer = 10, fraction = 0, message = "El id del estado de la reservación no puede ser un número entero")
    @NotBlank(message = "El id del estado de la reservación no puede estar vacío")
    Integer estadoReservacionId;
    @Digits(integer = 10, fraction = 0, message = "El id del tipo de reservación no puede ser un número entero")
    @NotBlank(message = "El id del tipo de reservación no puede estar vacío")
    Integer tipoReservacionId;
    @Digits(integer = 10, fraction = 0, message = "El id del cliente no puede ser un número entero")
    @NotBlank(message = "El id del cliente no puede estar vacío")
    Integer clienteId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "La fecha de ingreso no puede estar vacía")
    LocalDateTime fechaIngreso;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank(message = "La fecha de salida no puede estar vacía")
    LocalDateTime fechaSalida;
    @Digits(integer = 10, fraction = 0, message = "La cantidad de personas no puede ser un número entero")
    @NotBlank(message = "La cantidad de personas no puede estar vacía")
    Integer cantidadPersonas;
}
