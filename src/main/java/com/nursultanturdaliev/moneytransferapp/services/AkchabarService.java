package com.nursultanturdaliev.moneytransferapp.services;

import com.nursultanturdaliev.moneytransferapp.model.Currency;
import com.nursultanturdaliev.moneytransferapp.response.CurrencyInfo;
import com.nursultanturdaliev.moneytransferapp.response.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AkchabarService {

    @Autowired
    RestTemplate restTemplate;


    public CurrencyInfo getCurrencyInfo(Long amount) {
        String URL = "http://rates.akchabar.kg/get.json";
        CurrencyInfo currencyInfo = restTemplate.getForObject(URL, CurrencyInfo.class);

        Double afterFee = amount - (amount * 0.039) - 0.3;
        Double result = afterFee * currencyInfo.getRates().getUsd();
        currencyInfo.getRates().setUsd(result);

        return currencyInfo;
    }
}
