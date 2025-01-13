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
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String specification;
    @ManyToOne
    @JoinColumn(name = "suit_id")
    Suit suit;
}
