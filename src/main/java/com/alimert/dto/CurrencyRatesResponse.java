package com.alimert.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CurrencyRatesResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;

}
