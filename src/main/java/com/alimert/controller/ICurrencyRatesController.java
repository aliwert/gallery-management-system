package com.alimert.controller;

import com.alimert.dto.CurrencyRatesResponse;

public interface ICurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
