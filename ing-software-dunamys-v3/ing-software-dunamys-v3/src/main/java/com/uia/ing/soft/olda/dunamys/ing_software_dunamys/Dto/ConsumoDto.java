package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import java.sql.Date;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Cliente;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Inventario;

import lombok.Builder;
import lombok.Data;

@Data
public class ConsumoDto {
    private Integer id;

    private Inventario inventario;

    private Cliente cliente;

    private Integer cantidad;

    private Date fecha;

}
