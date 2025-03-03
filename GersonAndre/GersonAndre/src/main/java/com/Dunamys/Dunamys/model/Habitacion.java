package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitacion {
    @Id
    private Integer id;

    @Column(name = "numero_habitacion", nullable = false, unique = true)
    private Integer numeroHabitacion;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "estado_habitacion_id", nullable = false)
    private EstadoHabitacion estadoHabitacion;

    @ManyToOne
    @JoinColumn(name = "tipo_habitacion_id", nullable = false)
    private TipoHabitacion tipoHabitacion;
}
