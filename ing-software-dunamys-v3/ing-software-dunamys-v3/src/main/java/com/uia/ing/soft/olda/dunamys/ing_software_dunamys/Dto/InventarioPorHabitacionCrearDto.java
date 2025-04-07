package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InventarioPorHabitacionCrearDto {
    @Digits(integer = 10, fraction = 0, message = "El id de la reservación debe ser un número entero")
    @NotBlank(message = "El id de la reservación no puede estar vacío")
    private Integer id;
    @Digits(integer = 10, fraction = 0, message = "El id de la reservación debe ser un número entero")
    @NotBlank(message = "El id de la reservación no puede estar vacío")
    private Integer reservacionId;
    @Digits(integer = 10, fraction = 0, message = "El id del inventario debe ser un número entero")
    @NotBlank(message = "El id del inventario no puede estar vacío")
    private Integer inventarioId;
    @NotBlank(message = "El nombre del item no puede estar vacío")
    private String tipo;
    @Digits(integer = 10, fraction = 2, message = "El precio debe ser un número decimal de hasta 10 dígitos y 2 decimales")
    @NotBlank(message = "El precio no puede estar vacío")
    private Integer cantidad;
}
