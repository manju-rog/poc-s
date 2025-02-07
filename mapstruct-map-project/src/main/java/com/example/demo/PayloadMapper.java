package com.example.demo;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayloadMapper {
    PayloadMapper INSTANCE = Mappers.getMapper(PayloadMapper.class);

    @Mapping(source = "address.address1", target = "customerAddress1")
    @Mapping(source = "address.address2", target = "customerAddress2")
    OutputPayload toOutputPayload(InputPayload inputPayload);
}
