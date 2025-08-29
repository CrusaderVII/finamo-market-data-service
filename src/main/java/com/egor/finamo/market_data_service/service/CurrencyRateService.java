package com.egor.finamo.market_data_service.service;

import com.egor.finamo.market_data_service.data.CurrencyRateAmount;

import java.util.Collection;
import java.util.Currency;

public interface CurrencyRateService {

    CurrencyRateAmount getCurrencyRate(String targetCurrencyCode, String baseCurrencyCode);
}
