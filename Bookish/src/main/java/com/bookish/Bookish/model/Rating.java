package com.bookish.Bookish.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    @ManyToOne
    User user;
    @Column(nullable = false)
    @ManyToOne
    Book book;
    @Column(nullable = false)
    int rating;
    @Column(name = "rating_description")
    String ratingDescription;

}
