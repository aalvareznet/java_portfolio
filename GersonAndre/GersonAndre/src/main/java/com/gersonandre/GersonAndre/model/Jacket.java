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
public class Jacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double length;
    Double chest;
    Double waist;
    Double hip;
    Double back;
    Double shoulder;
    Double fit;
    Double lm;
    Double elbow;
    Double fist;
    Double neckline;
    String specification;
    String fabric;
}
