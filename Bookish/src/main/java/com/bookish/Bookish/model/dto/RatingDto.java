package com.bookish.Bookish.model.dto;

public record RatingDto(Long user, Long book, int rating, String description) {
}
