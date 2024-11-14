package com.alimert.dto;

import com.alimert.enums.CarStatusType;
import com.alimert.enums.CurrencyType;

import java.math.BigDecimal;

public class DtoCar extends DtoBase {
    private String plate;

    private String model;

    private String brand;

    private BigDecimal price;

    private Integer productionYear;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
