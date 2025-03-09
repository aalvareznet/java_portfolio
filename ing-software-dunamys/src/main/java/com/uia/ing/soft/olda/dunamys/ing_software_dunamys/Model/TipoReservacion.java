package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_reservacion", uniqueConstraints = @UniqueConstraint(columnNames = "tipo_reservacion"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoReservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_reservacion", nullable = false, length = 45)
    private String tipo;
}
