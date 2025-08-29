package com.egor.finamo.market_data_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ApiConfig {

    @Bean
    public FrankfurterProps frankfurterProps() {
        return new FrankfurterProps();
    }
}
