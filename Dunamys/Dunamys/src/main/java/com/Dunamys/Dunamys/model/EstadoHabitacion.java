package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoHabitacion {
    @Id
    private Integer id;

    @Column(name = "estado", nullable = false, length = 45)
    private String estado;
}
