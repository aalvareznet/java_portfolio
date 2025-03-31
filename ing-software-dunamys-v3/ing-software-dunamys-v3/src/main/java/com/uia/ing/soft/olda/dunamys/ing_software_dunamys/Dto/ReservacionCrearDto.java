package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import lombok.Data;

@Data   
@Valid
public class ReservacionCrearDto {
    @Digits(fraction = 0, integer = 10)
    Integer habitacionId;
    @Digits(fraction = 0, integer = 10)
    Integer estadoReservacionId;
    @Digits(fraction = 0, integer = 10)
    Integer tipoReservacionId;
    @Digits(fraction = 0, integer = 10)
    Integer clienteId;
    LocalDateTime fechaIngreso; //TODO: cambiar a LocalDateTime
    LocalDateTime fechaSalida;
    @Digits(fraction = 0, integer = 10)
    Integer cantidadPersonas;
}
