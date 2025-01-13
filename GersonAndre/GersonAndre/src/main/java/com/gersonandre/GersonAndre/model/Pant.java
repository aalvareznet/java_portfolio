package com.gersonandre.GersonAndre.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double length;
    Double crouch;
    Double waist;
    Double hip;
    Double knee;
    Double bottom;
    String specification;
    String fabric;
    @ManyToOne
    @JoinColumn(name = "suit_id")
    Suit suit;
}
