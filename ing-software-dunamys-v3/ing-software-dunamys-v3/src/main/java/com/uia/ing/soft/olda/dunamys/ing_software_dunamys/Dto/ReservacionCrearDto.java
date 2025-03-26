package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import lombok.Data;

@Data   
@Valid
public class ReservacionCrearDto {
    Integer habitacionId;

    Integer estadoReservacionId;

    Integer tipoReservacionId;

    Integer clienteId;

    Date fechaIngreso;

    Date fechaSalida;
    @Min(1)
    Integer cantidadPersonas;
}
