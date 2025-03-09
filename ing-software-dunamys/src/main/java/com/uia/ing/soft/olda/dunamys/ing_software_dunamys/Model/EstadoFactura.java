package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estado_factura", nullable = false, length = 45)
    private String estado;
}
