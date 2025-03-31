package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    @JsonFormat(pattern = "dd/MMM/yyyy")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "reservacion_id", nullable = false)
    private Reservacion reservacion;

    @ManyToOne
    @JoinColumn(name = "estado_factura_id", nullable = false)
    private EstadoFactura estadoFactura;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
