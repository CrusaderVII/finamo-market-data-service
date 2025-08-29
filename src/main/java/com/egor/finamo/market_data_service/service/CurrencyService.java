package com.egor.finamo.market_data_service.service;

import com.egor.finamo.market_data_service.data.Currency;

import java.util.Collection;

public interface CurrencyService {
    Collection<Currency> getAllCurrencies();
    Currency getCurrencyByCode(String code);
}
