package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_habitacion", nullable = false, unique = true)
    private Integer numeroHabitacion;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "precio", nullable = false)
    private Double precio;
    
    @Column(name = "imagen_url", nullable = false)
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "estado_habitacion_id", nullable = false)
    private EstadoHabitacion estadoHabitacion;

    @ManyToOne
    @JoinColumn(name = "tipo_habitacion_id", nullable = false)
    private TipoHabitacion tipoHabitacion;
}
