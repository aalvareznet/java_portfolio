package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "consumo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime fecha;
}
