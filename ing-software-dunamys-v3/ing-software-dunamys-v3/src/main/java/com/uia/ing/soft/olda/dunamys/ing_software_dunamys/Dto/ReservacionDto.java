package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReservacionDto {
    Integer id;

    Integer habitacionId;

    Integer estadoReservacionId;

    Integer tipoReservacionId;

    Integer clienteId;

    Date fechaIngreso;

    Date fechaSalida;

    Integer cantidadPersonas;
}
