package com.Dunamys.Dunamys.model;

import com.Dunamys.Dunamys.security.user.User;
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
