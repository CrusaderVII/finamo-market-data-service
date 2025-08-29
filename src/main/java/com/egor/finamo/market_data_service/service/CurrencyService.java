package com.egor.finamo.market_data_service.service;

import com.egor.finamo.market_data_service.repo.CurrencyRepository;
import com.egor.finamo.market_data_service.data.Currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public Collection<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
