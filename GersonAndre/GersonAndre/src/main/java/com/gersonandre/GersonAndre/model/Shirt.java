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
public class Shirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double neck;
    Double sleeve;
    String specification;
    String fabric;
    @ManyToOne
    @JoinColumn(name = "suit_id")
    Suit suit;
}
