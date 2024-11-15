package com.alimert.controller.impl;

import com.alimert.controller.BaseController;
import com.alimert.controller.ICurrencyRatesController;
import com.alimert.controller.RootEntity;
import com.alimert.dto.CurrencyRatesResponse;
import com.alimert.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/api")
public class CurrencyRatesControllerImpl extends BaseController implements ICurrencyRatesController {

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Override
    @GetMapping("/currency-rates")
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
    }
}
