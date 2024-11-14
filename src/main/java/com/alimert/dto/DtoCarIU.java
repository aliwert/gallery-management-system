package com.alimert.dto;


import com.alimert.enums.CarStatusType;
import com.alimert.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCarIU {

    @NotNull
    private String plate;

    @NotNull
    private String model;

    @NotNull
    private String brand;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer productionYear;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;
}
