package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    PaymentDTO toDTO(Payment payment);
    Payment toEntity(PaymentDTO paymentDTO);
}
