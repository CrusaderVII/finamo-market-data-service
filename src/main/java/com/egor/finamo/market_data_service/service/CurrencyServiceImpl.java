package com.egor.finamo.market_data_service.service;

import com.egor.finamo.market_data_service.repo.CurrencyRepository;
import com.egor.finamo.market_data_service.data.Currency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService{

    private static Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyRepository currencyRepository;

    @Override
    public Currency getCurrencyByCode(String code) {
        Currency currency = currencyRepository.findById(code)
                .orElseThrow();
        logger.info(currency.getCode());
        return currency;
    }

    @Override
    public Collection<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
