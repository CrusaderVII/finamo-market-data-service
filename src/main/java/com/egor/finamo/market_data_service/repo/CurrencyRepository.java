package com.egor.finamo.market_data_service.repo;

import com.egor.finamo.market_data_service.data.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}