package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FacturaDto {
    Integer id;

    LocalDateTime fecha;

    Integer reservacionId;

    Integer estadoFacturaId;

    Integer clienteId;
}
