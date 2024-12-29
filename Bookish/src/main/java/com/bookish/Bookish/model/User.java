package com.bookish.Bookish.model;

import com.bookish.Bookish.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String username;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
}
