package com.egor.finamo.market_data_service.data;

import lombok.*;

import java.math.BigDecimal;

/**
 * Class, that represents an exchange rate between two currencies.
 * <p>
 * For example, 1 USD = 90 RUB
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CurrencyRateAmount {

    /**
     * The numeric value of the exchange rate.
     * <p>
     * In example, it is rate which is 90
     */
    BigDecimal rate;

    /**
     * Currency being evaluated or converted to.
     * <p>
     * In example, it is USD. We convert RUB to USD, e.g. we want to know how many rubbles in dollars we have
     */
    Currency targetCurrency;

    /**
     * The base currency used for converting
     * <p>
     * In example, it is RUB. We convert RUB to USD, e.g. we want to know how many rubbles in dollars we have
     */
    Currency baseCurrency;

}
