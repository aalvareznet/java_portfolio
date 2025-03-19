package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidad_visitas", nullable = false)
    private Integer cantidadVisitas;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;
}
