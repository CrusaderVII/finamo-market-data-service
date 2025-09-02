package com.egor.finamo.market_data_service.service.impl;

import com.egor.finamo.market_data_service.config.FrankfurterProps;
import com.egor.finamo.market_data_service.data.api.FrankfurterApiDto;
import com.egor.finamo.market_data_service.service.ApiService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FrankfurterApiServiceImpl implements ApiService {

    private final FrankfurterProps props;

    private static final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public Collection<Map.Entry<String, BigDecimal>> getCurrencyRatesFromApi(Collection<String> targetCurrencyCodes, String baseCurrencyCode) throws
            URISyntaxException,
            IOException,
            InterruptedException {

        FrankfurterApiDto dto = getFrankfurterApiResponse(baseCurrencyCode);

         return dto.rates().entrySet().stream()
                 .filter(entry -> targetCurrencyCodes.contains(entry.getKey()))
                 .toList();
    }

    private FrankfurterApiDto getFrankfurterApiResponse(String baseCurrencyCode) throws
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

        return mapper.readValue(response.body(), FrankfurterApiDto.class);
    }
}
