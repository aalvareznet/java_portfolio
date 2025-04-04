package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_reservacion", uniqueConstraints = @UniqueConstraint(columnNames = "estado_reservacion"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoReservacion {
    @Id
    private Integer id;

    @Column(name = "estado_reservacion", nullable = false, length = 45)
    private String estado;
}
