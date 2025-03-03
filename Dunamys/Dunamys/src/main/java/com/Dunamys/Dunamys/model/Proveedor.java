package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proveedor", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre"),
        @UniqueConstraint(columnNames = "correo_referencia")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {
    @Id
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "correo_referencia", nullable = false, length = 45)
    private String correoReferencia;

    @Column(name = "telefono", nullable = false, length = 45)
    private String telefono;
}
