package com.Dunamys.Dunamys.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria_inventario", uniqueConstraints = @UniqueConstraint(columnNames = "inventory_category"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaInventario {
    @Id
    private Integer id;

    @Column(name = "inventory_category", nullable = false, length = 45)
    private String categoria;
}
