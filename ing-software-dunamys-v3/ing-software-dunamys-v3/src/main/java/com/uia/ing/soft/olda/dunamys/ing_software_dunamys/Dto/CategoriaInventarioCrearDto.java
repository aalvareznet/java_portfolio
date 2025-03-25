package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Valid
public class CategoriaInventarioCrearDto {
    @NotBlank(message = "La categoria no puede estar en blanco")
    @NotEmpty(message = "La categoria no puede estar vacia")
    @Size(min = 3, max = 20, message = "La categoria debe tener un minimo de 3 caracteres y un maximo de 20")
    String categoria;
}
