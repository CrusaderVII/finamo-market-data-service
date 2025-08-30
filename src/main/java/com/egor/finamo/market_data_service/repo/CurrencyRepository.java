package com.egor.finamo.market_data_service.repo;

import com.egor.finamo.market_data_service.data.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

    @Query(value = "SELECT * FROM currency c order by priority ASC LIMIT :limit", nativeQuery = true)
    Collection<Currency> findMainCurrencies(@Param("limit") int limit);
}