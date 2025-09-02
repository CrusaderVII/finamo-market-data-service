package com.egor.finamo.market_data_service.service;

import com.egor.finamo.market_data_service.data.CurrencyRateAmount;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

public interface CurrencyRateService {

    Collection<CurrencyRateAmount> getCurrencyRates(Collection<String> targetCurrencyCode, String baseCurrencyCode);
}