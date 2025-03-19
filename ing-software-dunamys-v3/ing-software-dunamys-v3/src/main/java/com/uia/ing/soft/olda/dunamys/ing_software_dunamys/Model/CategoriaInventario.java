package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "inventory_category", nullable = false, length = 45)
    private String categoria;
}
