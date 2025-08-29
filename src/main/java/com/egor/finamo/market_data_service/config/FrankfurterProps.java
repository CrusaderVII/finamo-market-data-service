package com.egor.finamo.market_data_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "api.frankfurter")
@Getter
@Setter
public class FrankfurterProps {

    private String base;
    Map<String, String> latest;

    public String getLatestRateUri() {
        return base+latest.get("with-base");
    }
}
