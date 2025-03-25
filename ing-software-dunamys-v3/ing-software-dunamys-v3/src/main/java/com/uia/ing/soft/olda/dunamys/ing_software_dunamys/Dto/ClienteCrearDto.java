package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Valid
public class ClienteCrearDto {
    @Size(min = 2, max = 45, message = "El campo debe tener minimo 2 caracteres y maximo 45 caracteres")
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    String nombre;
    @Size(min = 2, max = 45, message = "El campo debe tener minimo 2 caracteres y maximo 45 caracteres")
    @NotEmpty(message = "El campo primer apellido no puede estar vacio")
    String primerApellido;
    @Size(min = 2, max = 45, message = "El campo debe tener minimo 2 caracteres y maximo 45 caracteres")
    @NotEmpty(message = "El campo segundo apellido no puede estar vacio")
    String segundoApellido;
    @Size(min = 8,max = 8, message = "El campo debe tener 8 caracteres. No usar guion")
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    String telefono;
    @Email(message = "El formato no es adecuado")
    String correo;
    @NotEmpty(message = "El campo pais no puede estar vacio")
    String pais;
    @Size(min = 8, max = 15, message = "El campo debe tener minimo 8 caracteres y maximo 15 caracteres")
    @NotEmpty(message = "El campo usuario no puede estar vacio")
    String username;
    @Size(min = 8, max = 15, message = "El campo debe tener minimo 8 caracteres y maximo 15 caracteres")
    @NotEmpty(message = "El campo password no puede estar vacio")
    String password;
}
