package dev.benew.infomining_api_example_java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.dto.ResultData;
import dev.benew.infomining_api_example_java.dto.StatusResponse;
import dev.benew.infomining_api_example_java.dto.step3.Step3FollowUpResponse;
import dev.benew.infomining_api_example_java.dto.step3.Step3QuestionResponse;
import dev.benew.infomining_api_example_java.dto.step3.Step3ReportResponse;
import dev.benew.infomining_api_example_java.dto.step3.Step3TotalResponse;
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
 *  #4
 * Step3Scenario
 */
@Slf4j
@Component
public class Step3Scenario {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    private final String baseUrl = "https://api.infomining-dev.com/rest_api";

    public Step3Scenario() {
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
     *   Step3 Scenario Question List by Saved Answer
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param reportId <- identifier of the report (required)
     */
    public ResponseEntity<ResultData<List<Step3QuestionResponse>>> questions(String accessToken, String languageType,
                                                                             String reportId) {
        log.info("Step3 - questions");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("report_id", reportId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step3/questions")
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
                new ParameterizedTypeReference<ResultData<List<Step3QuestionResponse>>>() {}
        );
    }

    /**
     * @apiNote
     *   Step 3 List of options for the question
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param reportId <- identifier of the report (required)
     * @param questionId <- Step3 identifier of the question (required)
     */
    public ResponseEntity<ResultData<Step3TotalResponse>> question(String accessToken, String languageType,
                                                                   String reportId, String questionId) {
        log.info("Step3 - question");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("report_id", reportId);
        params.add("question_id", questionId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step3/question")
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
                new ParameterizedTypeReference<ResultData<Step3TotalResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step3 Additional Question Information
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param followUpId <- Step3 Additional Question Identifiers for Questions (required)
     */
    public ResponseEntity<ResultData<Step3FollowUpResponse>> followUp(String accessToken, String languageType,
                                                                      String followUpId) {
        log.info("Step3 - followUp");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("follow_up_id", followUpId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step3/followUp")
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
                new ParameterizedTypeReference<ResultData<Step3FollowUpResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step3 Save Report
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     * @param questionId <- Step3 identifier of the question (optional)
     * @param followupQuestionId <- Step3 identifier of the followup (optional)
     * @param selectionId <- Step3 identifier of the selection (optional)
     * @param inputTxt <- Step3 answer to a question(Subjective) (optional)
     * @param questionType <- objective, subjective (required)
     */
    public ResponseEntity<ResultData<StatusResponse>> saveStep3Report(String accessToken,
                                                                      String reportId, String questionId, String followupQuestionId, String selectionId, String inputTxt, String questionType) {
        log.info("Step3 - saveStep3Report");
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step3/saveReport")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);
        if (questionId != null)
            params.add("question_id", questionId);
        if (followupQuestionId != null)
            params.add("followup_question_id", followupQuestionId);
        if (selectionId != null)
            params.add("selection_id", selectionId);
        if (inputTxt != null)
            params.add("input_txt", inputTxt);
        params.add("question_type", questionType);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                new ParameterizedTypeReference<ResultData<StatusResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step 3 of the report Question and answer information
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     */
    public ResponseEntity<ResultData<List<Step3ReportResponse>>> step3History(String accessToken,
                                                                              String reportId) {
        log.info("Step3 - step3History");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step3/history")
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
                new ParameterizedTypeReference<ResultData<List<Step3ReportResponse>>>() {}
        );
    }

    /**
     * @apiNote
     *   Last Report Summary
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     */
    public ResponseEntity<ResultData<List<Step3ReportResponse>>> reportEnd(String accessToken,
                                                                           String reportId) {
        log.info("Step3 - reportEnd");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/reportEnd")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                new ParameterizedTypeReference<ResultData<List<Step3ReportResponse>>>() {}
        );
    }
}
