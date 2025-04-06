package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;


import lombok.Data;

@Data
public class HabitacionDto {
    
    Integer id;

    Integer numeroHabitacion;

    Double precio;

    Integer capacidad;

    Integer estadoHabitacionId;

    Integer tipoHabitacionId;

    String imagenUrl;
}
