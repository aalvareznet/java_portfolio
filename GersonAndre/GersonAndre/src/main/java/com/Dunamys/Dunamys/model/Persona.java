package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {
    @Id
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "primer_apellido", nullable = false, length = 45)
    private String primerApellido;

    @Column(name = "segundo_apellido", nullable = false, length = 45)
    private String segundoApellido;

    @Column(name = "telefono", nullable = false, length = 45)
    private String telefono;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @Column(name = "pais", nullable = false, length = 45)
    private String pais;
}
