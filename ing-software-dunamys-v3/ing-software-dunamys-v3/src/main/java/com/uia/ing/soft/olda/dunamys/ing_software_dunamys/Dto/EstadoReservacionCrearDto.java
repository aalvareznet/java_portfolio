package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstadoReservacionCrearDto {
    @NotBlank(message = "El id del estado de la reservación no puede estar vacío")
    String estado;
}
