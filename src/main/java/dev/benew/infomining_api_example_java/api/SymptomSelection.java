package dev.benew.infomining_api_example_java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.dto.ResultData;
import dev.benew.infomining_api_example_java.dto.step1.Step1QuestionResponse;
import dev.benew.infomining_api_example_java.dto.symptom.SymptomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 *  #3
 * Symptom Selection
 */
@Slf4j
@Component
public class SymptomSelection {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    private final String baseUrl = "https://api.infomining-dev.com/rest_api";

    public SymptomSelection() {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus httpStatus = response.getStatusCode();

                return httpStatus.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });
        mapper.registerModule(new JavaTimeModule());
    }

    /**
     * @apiNote
     *   Symptom Search
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param reportId <- identifier of the report (required)
     * @param param <- search word (required)
     */
    public ResponseEntity<ResultData<List<SymptomResponse>>> symptoms(String accessToken, String languageType,
                                                                      String reportId, String param) {
        log.info("SymptomSelection - symptoms");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("report_id", reportId);
        params.add("param", param);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/symptom/symptoms")
                .queryParams(params)
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<ResultData<List<SymptomResponse>>>() {}
        );
    }

    /**
     * @apiNote
     *   Machine learning Symptom Search
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param reportId <- identifier of the report (required)
     * @param param <- search word (required)
     */
    public ResponseEntity<ResultData<List<SymptomResponse>>> MLSymptoms(String accessToken, String languageType,
                                                                        String reportId, String param) {
        log.info("SymptomSelection - MLSymptoms");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("report_id", reportId);
        params.add("param", param);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/symptom/MLsymptoms")
                .queryParams(params)
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<ResultData<List<SymptomResponse>>>() {}
        );
    }
}
