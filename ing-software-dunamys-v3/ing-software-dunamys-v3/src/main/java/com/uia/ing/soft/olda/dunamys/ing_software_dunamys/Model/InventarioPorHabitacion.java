package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario_por_habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioPorHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Reservacion reservacion;
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private InventarioPorHabitacionTipo tipo;
}
