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
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String specification;
    String leather;
}
