package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {
    @Id
    private Integer id;

    @Column(name = "nombre_item", nullable = false, length = 45)
    private String nombreItem;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_inventario_id", nullable = false)
    private CategoriaInventario categoriaInventario;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;
}
