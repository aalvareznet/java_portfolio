package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.sql.Date;

import lombok.Data;
@Data
public class ConsumoCrearDto {

    Integer inventarioId;

    Integer clienteId;

    Integer cantidad;

    Date fecha;
}
