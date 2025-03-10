package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CrearClienteDto {
    private String nombre;

    private String primerApellido;

    private String segundoApellido;

    private String telefono;

    private String correo;

    private String pais;

    private String username;

    private String password;

}
