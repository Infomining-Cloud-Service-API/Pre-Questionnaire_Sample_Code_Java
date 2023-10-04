package dev.benew.infomining_api_example_java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.dto.ResultData;
import dev.benew.infomining_api_example_java.dto.StatusResponse;
import dev.benew.infomining_api_example_java.dto.step2.Step2DepartmentsResponse;
import dev.benew.infomining_api_example_java.dto.step2.Step2QuestionResponse;
import dev.benew.infomining_api_example_java.dto.step2.Step2ReportResponse;
import dev.benew.infomining_api_example_java.dto.step2.Step2TotalResponse;
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
 * Step2Scenario
 */
@Slf4j
@Component
public class Step2Scenario {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    private final String baseUrl = "https://api.infomining-dev.com/rest_api";

    public Step2Scenario() {
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
     *   Step2 Save Symptom Selection Report
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     * @param symptomId <- identifier of the symptom (required)
     */
    public ResponseEntity<ResultData<StatusResponse>> symptomSelection(String accessToken,
                                                                       String reportId, String symptomId) {
        log.info("Step2 - symptomSelection");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step2/symptomSelect")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);
        params.add("symptom_id", symptomId);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                new ParameterizedTypeReference<ResultData<StatusResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   a list of medical departments by report
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param reportId <- identifier of the report (required)
     */
    public ResponseEntity<ResultData<Step2DepartmentsResponse>> departments(String accessToken, String languageType,
                                                                            String reportId) {
        log.info("Step2 - departments");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("report_id", reportId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step2/departments")
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
                new ParameterizedTypeReference<ResultData<Step2DepartmentsResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step2 Question List by Symptom
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param symptomId <- identifier of the symptom (required)
     */
    public ResponseEntity<ResultData<List<Step2QuestionResponse>>> questions(String accessToken, String languageType,
                                                                             String symptomId) {
        log.info("Step2 - questions");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("symptom_id", symptomId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step2/questions")
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
                new ParameterizedTypeReference<ResultData<List<Step2QuestionResponse>>>() {}
        );
    }

    /**
     * @apiNote
     *   Step 2 List of options for the question
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param questionId <- Step2 identifier of the question (required)
     * @param reportId <- identifier of the report (required)
     */
    public ResponseEntity<ResultData<Step2TotalResponse>> question(String accessToken, String languageType,
                                                                   String questionId, String reportId) {
        log.info("Step2 - question");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType);
        params.add("question_id", questionId);
        params.add("report_id", reportId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step2/question")
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
                new ParameterizedTypeReference<ResultData<Step2TotalResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step2 Question Information by Branch Question Selection
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param languageType <- ex) kr, en (optional)
     * @param selectionId <- Step2 identifier of the selection (required)
     */
    public ResponseEntity<ResultData<Step2QuestionResponse>> branchQuestion(String accessToken, String languageType,
                                                                            String selectionId) {
        log.info("Step2 - branchQuestion");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (languageType != null)
            params.add("language_type", languageType); // kr, en
        params.add("selection_id", selectionId);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/step2/branchQuestion")
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
                new ParameterizedTypeReference<ResultData<Step2QuestionResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   Step2 Save Report
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     * @param questionId <- Step2 identifier of the question (required)
     * @param selectionId <- Step2 identifier of the selection (optional)
     * @param inputTxt <- Step2 answer to a question(Subjective) (optional)
     * @param questionType <- objective, subjective (required)
     */
    public ResponseEntity<ResultData<StatusResponse>> saveStep2Report(String accessToken,
                                             String reportId, String questionId, String selectionId, String inputTxt, String questionType) {
        log.info("Step2 - saveStep2Report");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/step2/saveReport")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);
        params.add("question_id", questionId);
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
     *   Step 2 of the report Question and answer information
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     */
    public ResponseEntity<ResultData<List<Step2ReportResponse>>> step2History(String accessToken,
                                                                              String reportId) {
        log.info("Step2 - step2History");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("report_id", reportId)
                .path("/v1/report/step2/history")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);



        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<ResultData<List<Step2ReportResponse>>>() {}
        );
    }
}
