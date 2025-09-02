package com.egor.finamo.market_data_service.controller;

import com.egor.finamo.market_data_service.data.Currency;

import com.egor.finamo.market_data_service.data.CurrencyRateAmount;
import com.egor.finamo.market_data_service.service.CurrencyRateService;
import com.egor.finamo.market_data_service.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DataController {

    private final CurrencyService currencyService;
    private final CurrencyRateService currencyRateService;

    @QueryMapping
    public Collection<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @QueryMapping
    public Collection<CurrencyRateAmount> getCurrencyRates(@Argument Collection<String>  targetCurrencyCodes,
                                               @Argument String baseCurrencyCode) {
        return currencyRateService.getCurrencyRates(targetCurrencyCodes, baseCurrencyCode);
    }

    @QueryMapping
    public Collection<Currency> getMainCurrencies() {
        log.info("YES");
        Collection<Currency> currencies = currencyService.getMainCurrencies();
        log.info(currencies + "");
        return currencies;
    }
}
