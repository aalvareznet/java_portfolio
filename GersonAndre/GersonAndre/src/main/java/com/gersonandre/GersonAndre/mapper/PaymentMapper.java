package com.gersonandre.GersonAndre.mapper;

import com.gersonandre.GersonAndre.dto.PaymentDto;
import com.gersonandre.GersonAndre.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto paymentToPaymentDto(Payment payment);
    Payment paymentDtoToPayment(PaymentDto paymentDto);
}
