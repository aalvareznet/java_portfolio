package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Valid
public class CategoriaInventarioCrearDto {
    @NotEmpty
    String categoria;
}
