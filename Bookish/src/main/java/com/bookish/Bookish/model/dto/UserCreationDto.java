package com.bookish.Bookish.model.dto;

import com.bookish.Bookish.model.enums.Role;

public record UserCreationDto(String userName, String email, Role role) {
}
