package com.gersonandre.GersonAndre.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Suit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "jacket_id", nullable = false)
    private Jacket jacket;

    @OneToOne
    @JoinColumn(name = "pant_id", nullable = false)
    private Pant pant;

    @OneToOne
    @JoinColumn(name = "shirt_id", nullable = false)
    private Shirt shirt;

    @OneToOne
    @JoinColumn(name = "shoe_id", nullable = false)
    private Shoe shoe;

    @OneToOne
    @JoinColumn(name = "vest_id", nullable = false)
    private Vest vest;

    @OneToOne
    @JoinColumn(name = "suit_accessory", nullable = false)
    private Accessory accessories;
}
