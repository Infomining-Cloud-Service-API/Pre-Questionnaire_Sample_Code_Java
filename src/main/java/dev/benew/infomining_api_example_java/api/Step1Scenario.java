package dev.benew.infomining_api_example_java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.dto.ResultData;
import dev.benew.infomining_api_example_java.dto.step1.Step1QuestionResponse;
import dev.benew.infomining_api_example_java.dto.StatusResponse;
import dev.benew.infomining_api_example_java.dto.step1.Step1ReportResponse;
import dev.benew.infomining_api_example_java.dto.step1.Step1TotalResponse;
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
import java.util.Collections;
import java.util.List;

/**
 *  #2
 * Step1Scenario
 */
@Slf4j
@Component
public class Step1Scenario {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();
    private final String baseUrl = "https://api.infomining-dev.com/rest_api";
    
    public Step1Scenario() {
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
     *   Step 1 Call the entire question
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     */
    public ResponseEntity<ResultData<List<Step1QuestionResponse>>> getQuestions(String accessToken, String languageType) {
        log.info("Step1 - getQuestions");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step1/questions")
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
                new ParameterizedTypeReference<ResultData<List<Step1QuestionResponse>>>() {}
        );
    }

    /**
     * @apiNote
     *   Step 1 List of options for the question
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param questionId <- step1 identifier of the question (required)
     */
    public ResponseEntity<ResultData<Step1TotalResponse>> question(String accessToken, String languageType,
                                                                   String questionId) {
        log.info("Step1 - getQuestion");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("question_id", questionId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step1/question")
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
                new ParameterizedTypeReference<ResultData<Step1TotalResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step 1 Save the report (for each question)
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     * @param questionId <- Step1 identifier of the question (required)
     * @param selectionId <- Step1 identifier of the selection (required)
     * @param inputTxt <- Step1 선택지의 내용(주관식일 경우에만 존재) (optional)
     */
    public ResponseEntity<ResultData<StatusResponse>> saveStep1Report(String accessToken,
                                                                      String reportId, String questionId, String selectionId, String inputTxt) {
        log.info("Step1 - saveStep1Report");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step1/saveReport")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);
        params.add("question_id", questionId);
        params.add("selection_id", selectionId);
        if (inputTxt != null)
            params.add("input_txt", inputTxt);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                new ParameterizedTypeReference<ResultData<StatusResponse>>() {}
        );
    }
    /**
     * @apiNote
     *   Save user information in step1
     *
     * @param reportId <- identifier of the report (required)
     * @param userAge <- (required)
     * @param userHeight <- (required)
     * @param userWeight <- (required)
     */
    public ResponseEntity<ResultData<StatusResponse>> saveReportUserInfo(String accessToken, String reportId, Integer userAge, Integer userHeight, Integer userWeight) {
        log.info("Step1 - saveReportUserInfo");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step1/saveReportUserInfo")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);
        params.add("user_age", userAge.toString());
        params.add("user_height", userHeight.toString());
        params.add("user_weight", userWeight.toString());

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                new ParameterizedTypeReference<ResultData<StatusResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step 1 of the report Question and answer information
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     */
    public ResponseEntity<ResultData<List<Step1ReportResponse>>> step1History(String accessToken,
                                                                              String reportId) {
        log.info("Step1 - step1History");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step1/history")
                .queryParam("report_id", reportId)
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<ResultData<List<Step1ReportResponse>>>() {}
        );
    }
}
