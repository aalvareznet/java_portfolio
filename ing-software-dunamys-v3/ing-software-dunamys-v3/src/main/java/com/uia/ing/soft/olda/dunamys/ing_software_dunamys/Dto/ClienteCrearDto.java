package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteCrearDto {
    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre;
    @NotBlank(message = "El primer apellido no puede estar vacío")
    String primerApellido;
    @NotBlank(message = "El segundo apellido no puede estar vacío")
    String segundoApellido;
    @NotBlank(message = "La fecha de nacimiento no puede estar vacía")
    String telefono;
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo no es válido")
    String correo;
    @NotBlank(message = "El pais no puede estar vacía")
    String pais;
    @NotBlank(message = "El nombre de usuario no puede estar vacía")
    @Size(min = 6, max = 12, message = "El nombre de usuario debe tener entre 6 y 12 caracteres")
    String username;
    @NotBlank(message = "La contrasena no puede estar vacía")
    @Size(min = 8, max = 16, message = "La contrasena debe tener entre 8 y 16 caracteres")
    String password;
}
