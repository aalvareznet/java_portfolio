package com.gersonandre.GersonAndre.dto;

public record PaymentDto(Double netAmount
                        , Double taxes
                        , Double totalAmount) {
}
