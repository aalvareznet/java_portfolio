package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ClienteDto {
    Integer id;

    String nombre;

    String primerApellido;

    String segundoApellido;

    String telefono;

    String correo;

    String pais;

    String username;

    String password;
}
