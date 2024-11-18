package com.alimert.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSoldCarIU {


    @NotNull
    private Long customerId;

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;
}
