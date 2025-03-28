package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.util.Date;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
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
    Date fechaIngreso; //TODO: cambiar a LocalDateTime
    Date fechaSalida;
    @Digits(fraction = 0, integer = 10)
    Integer cantidadPersonas;
}
