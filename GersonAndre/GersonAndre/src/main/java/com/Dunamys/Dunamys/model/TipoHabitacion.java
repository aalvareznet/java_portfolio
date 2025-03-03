package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoHabitacion {
    @Id
    private Integer id;

    @Column(name = "tipo", nullable = false, length = 45)
    private String tipo;
}
