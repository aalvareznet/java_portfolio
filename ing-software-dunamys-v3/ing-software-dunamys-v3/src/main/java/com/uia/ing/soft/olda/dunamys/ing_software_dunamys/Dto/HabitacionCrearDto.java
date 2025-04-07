package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HabitacionCrearDto {
    @Digits(integer = 3, fraction = 0, message = "El número de habitación debe ser un número entero de hasta 3 dígitos")
    @NotBlank(message = "El número de habitación no puede estar vacío")
    Integer numeroHabitacion;
    @Digits(integer = 10, fraction = 2, message = "El precio debe ser un número decimal de hasta 10 dígitos y 2 decimales")
    @NotBlank(message = "El precio no puede estar vacío")
    Double precio;
    @Digits(integer = 3, fraction = 0, message = "La capacidad debe ser un número entero de hasta 3 dígitos")
    @NotBlank(message = "La capacidad no puede estar vacía")
    Integer capacidad;
    @Digits(integer = 10, fraction = 0, message = "El id del estado de la habitación debe ser un número entero")
    @NotBlank(message = "El id del estado de la habitación no puede estar vacío")
    Integer estadoHabitacionId;
    @Digits(integer = 10, fraction = 0, message = "El id del tipo de habitación debe ser un número entero")
    @NotBlank(message = "El id del tipo de habitación no puede estar vacío")
    Integer tipoHabitacionId;
    @NotBlank(message = "El URL no puede estar vacía")
    String imagenUrl;

}
