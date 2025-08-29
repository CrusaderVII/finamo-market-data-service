package com.egor.finamo.market_data_service.data.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public record FrankfurterApiDto(BigDecimal amount, String base, LocalDate date, Map<String, BigDecimal> rates) {
}
