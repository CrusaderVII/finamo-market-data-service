package com.egor.finamo.market_data_service.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface ApiService {
    BigDecimal getCurrencyRateFromApi(String targetCurrencyCode, String baseCurrencyCode) throws
            URISyntaxException,
            IOException,
            InterruptedException;
}
