package dev.benew.infomining_api_example_java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.dto.ResultData;
import dev.benew.infomining_api_example_java.dto.ReportIdResponse;
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

/**
 *  #1
 * Preparation Scenario
 */
@Slf4j
@Component
public class PreparationScenario {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    private final String baseUrl = "https://api.infomining-dev.com/rest_api";

    public PreparationScenario() {
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
     *   Generating a report
     *
     * @param accessToken <- Your_Access_Token (required)
     */
    public ResponseEntity<ResultData<ReportIdResponse>> reportStart(String accessToken) {
        log.info("PreparationScenario - reportStart");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/reportStart")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<ResultData<ReportIdResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step1 Save the report (enter at once)
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param userName <- (required)
     * @param userGender <- (M:Male, F:Female) (required)
     * @param userAge <- (required)
     * @param userPregnant <- Pregnancy status of user (preg:pregnant, null:not pregnant) (F <- required, M <- optional)
     * @param userHeight <- (optional)
     * @param userWeight <- (optional)
     * @param userJob <- (optional)
     * @param userReligion <- (optional)
     */
    public ResponseEntity<ResultData<ReportIdResponse>> saveReportTotal(String accessToken,
                                                                        String userName, String userGender, String userAge, String userPregnant,
                                                                        String userHeight, String userWeight, String userJob, String userReligion) {
        log.info("PreparationScenario- saveReportTotal");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step1/saveReportTotal")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("user_name", userName);
        params.add("user_gender", userGender);
        params.add("user_age", userAge);
        if (userPregnant != null)
            params.add("user_pregnant", userPregnant);
        if (userHeight != null)
            params.add("user_height", userHeight);
        if (userWeight != null)
            params.add("user_weight", userWeight);
        if (userJob != null)
            params.add("user_job", userJob);
        if (userReligion != null)
            params.add("user_religion", userReligion);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                new ParameterizedTypeReference<ResultData<ReportIdResponse>>() {}
        );
    }


}
