package com.gersonandre.GersonAndre.dto;

import lombok.Builder;

import java.util.Date;

public record ClientDto(String name
                        , String address
                        , String phone1
                        , String phone2
                        , String email
                        , Date delDate
                        , Date actDate) {
}
