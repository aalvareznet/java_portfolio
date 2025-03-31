package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReservacionDto {
    Integer id;

    Integer habitacionId;

    Integer estadoReservacionId;

    Integer tipoReservacionId;

    Integer clienteId;

    LocalDateTime fechaIngreso;

    LocalDateTime fechaSalida;

    Integer cantidadPersonas;
}
