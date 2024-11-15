package com.alimert.service.impl;

import com.alimert.dto.CurrencyRatesResponse;
import com.alimert.exception.BaseException;
import com.alimert.exception.ErrorMessage;
import com.alimert.exception.MessageType;
import com.alimert.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {
    
    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A.YTL";
        String type = "json";

        String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders headers = new HttpHeaders();

        headers.set("key", "fzuqRNj5sv");

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CurrencyRatesResponse> exchange = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
            });
            if (exchange.getStatusCode().is2xxSuccessful()) {
                return exchange.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
        }
        return null;
    }
}
