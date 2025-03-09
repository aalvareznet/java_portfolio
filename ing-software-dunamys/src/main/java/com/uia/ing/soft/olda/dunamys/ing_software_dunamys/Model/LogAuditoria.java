package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_auditoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Column(name = "accion", nullable = false, length = 45)
    private String accion;

    @Column(name = "tabla_afectada", nullable = false, length = 45)
    private String tablaAfectada;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
}
