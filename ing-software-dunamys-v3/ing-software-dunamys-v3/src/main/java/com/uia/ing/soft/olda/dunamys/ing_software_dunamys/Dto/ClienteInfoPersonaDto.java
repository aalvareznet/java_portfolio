package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import lombok.Data;

@Data
public class ClienteInfoPersonaDto {
    Integer id;

    Integer cantidadVisitas;

    Integer usuarioId;

    Integer personaId;

    String nombrePersona;

    String primerApellidoPersona;

    String segundoApellidoPerson;

    String email;

    String telefono;

    String pais;
}
