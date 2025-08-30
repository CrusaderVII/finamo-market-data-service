package com.egor.finamo.market_data_service.service.impl;

import com.egor.finamo.market_data_service.data.Currency;
import com.egor.finamo.market_data_service.data.CurrencyRateAmount;
import com.egor.finamo.market_data_service.service.ApiService;
import com.egor.finamo.market_data_service.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyServiceImpl currencyService;
    private final ApiService apiService;

    @Override
    public CurrencyRateAmount getCurrencyRate(String targetCurrencyCode, String baseCurrencyCode) {
        Currency targetCurrency = currencyService.getCurrencyByCode(targetCurrencyCode);
        Currency baseCurrency = currencyService.getCurrencyByCode(baseCurrencyCode);

        BigDecimal amount;
        try {
            amount = apiService.getCurrencyRateFromApi(targetCurrencyCode, baseCurrencyCode);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CurrencyRateAmount.builder()
                .rate(amount)
                .targetCurrency(targetCurrency)
                .baseCurrency(baseCurrency)
                .build();
    }

}
