package com.bookish.Bookish.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    List<String> author;
    @Column(nullable = false)
    List<String> genre;
    @Column(nullable = false)
    String description;
    @Column(nullable = false, name = "average_rating")
    double averageRating;
    @Column(name = "image_url")
    String imageUrl;
    @Column(nullable = false, name = "number_of_pages")
    int numberOfPages;
}
