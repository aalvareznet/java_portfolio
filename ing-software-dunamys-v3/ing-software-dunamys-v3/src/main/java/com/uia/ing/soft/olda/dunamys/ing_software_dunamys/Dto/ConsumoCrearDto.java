package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ConsumoCrearDto {

    Integer inventarioId;

    LocalDateTime fecha;

    Integer clienteId;

    Integer cantidad;

}
