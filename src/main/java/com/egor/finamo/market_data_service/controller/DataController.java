package com.egor.finamo.market_data_service.controller;

import com.egor.finamo.market_data_service.data.Currency;

import com.egor.finamo.market_data_service.data.CurrencyRateAmount;
import com.egor.finamo.market_data_service.service.CurrencyRateService;
import com.egor.finamo.market_data_service.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class DataController {

    private final CurrencyService currencyService;
    private final CurrencyRateService currencyRateService;

    @QueryMapping
    public Collection<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @QueryMapping
    public CurrencyRateAmount getCurrencyRate(@Argument String targetCurrencyCode, @Argument String baseCurrencyCode) {
        return currencyRateService.getCurrencyRate(targetCurrencyCode, baseCurrencyCode);
    }

    @QueryMapping
    public Collection<Currency> getMainCurrencies() {
        return currencyService.getMainCurrencies();
    }
}
