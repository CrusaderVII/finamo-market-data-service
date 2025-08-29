package com.egor.finamo.market_data_service.service;

import com.egor.finamo.market_data_service.config.FrankfurterProps;
import com.egor.finamo.market_data_service.data.CurrencyRateAmount;
import com.egor.finamo.market_data_service.data.api.FrankfurterApiDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class FrankfurterApiServiceImpl {

    private final FrankfurterProps props;

    private static final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public BigDecimal getCurrencyRateFromApi(String targetCurrencyCode, String baseCurrencyCode) throws
            URISyntaxException,
            IOException,
            InterruptedException {
        String uri = String.format(props.getLatestRateUri(), baseCurrencyCode);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        FrankfurterApiDto dto = mapper.readValue(response.body(), FrankfurterApiDto.class);

        return dto.rates().get(targetCurrencyCode);
    }
}
