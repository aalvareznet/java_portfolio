package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "reservacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "estado_reservacion_id", nullable = false)
    private EstadoReservacion estadoReservacion;

    @ManyToOne
    @JoinColumn(name = "tipo_reservacion_id", nullable = false)
    private TipoReservacion tipoReservacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso;

    @Column(name = "fecha_salida", nullable = false)
    private Date fechaSalida;

    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidadPersonas;
}
