package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_factura", uniqueConstraints = @UniqueConstraint(columnNames = "estado_factura"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoFactura {
    @Id
    private Integer id;

    @Column(name = "estado_factura", nullable = false, length = 45)
    private String estado;
}
