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
    Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
    @OneToOne(mappedBy = "suit")
    Payment payment;
    @OneToOne(mappedBy = "suit")
    Jacket jacket;
    @OneToOne(mappedBy = "suit")
    Pant pant;
    @OneToOne(mappedBy = "suit")
    Shirt shirt;
    @OneToOne(mappedBy = "suit")
    Shoe shoe;
    @OneToOne(mappedBy = "suit")
    Vest vest;
    @OneToMany(mappedBy = "suit")
    Set<Accessory> accessories;
}
