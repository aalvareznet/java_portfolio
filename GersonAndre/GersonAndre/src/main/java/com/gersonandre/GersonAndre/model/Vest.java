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
public class Vest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double length;
    Double chest;
    Double waist;
    Double hip;
    Double back;
    Double shoulder;
    Double bm;
    Double neckline;
    String specification;
    String fabric;
    @ManyToOne
    @JoinColumn(name = "suit_id")
    Suit suit;

}
