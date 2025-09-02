package com.egor.finamo.market_data_service.service.impl;

import com.egor.finamo.market_data_service.data.Currency;
import com.egor.finamo.market_data_service.data.CurrencyRateAmount;
import com.egor.finamo.market_data_service.service.ApiService;
import com.egor.finamo.market_data_service.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyServiceImpl currencyService;
    private final ApiService apiService;

    @Override
    public Collection<CurrencyRateAmount> getCurrencyRates(Collection<String> targetCurrencyCodes, String baseCurrencyCode) {
        Currency baseCurrency = currencyService.getCurrencyByCode(baseCurrencyCode);

        Collection<Map.Entry<String, BigDecimal>> amounts = new ArrayList<>();
        try {
            amounts = apiService.getCurrencyRatesFromApi(targetCurrencyCodes, baseCurrencyCode);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return amounts.stream()
                .map(entry -> new CurrencyRateAmount(entry.getValue(),
                            currencyService.getCurrencyByCode(entry.getKey()),
                            baseCurrency))
                .toList();
    }

}
