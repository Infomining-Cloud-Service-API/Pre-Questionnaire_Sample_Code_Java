package dev.benew.infomining_api_example_java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.dto.ResultData;
import dev.benew.infomining_api_example_java.dto.report.ReportResponse;
import dev.benew.infomining_api_example_java.dto.step3.Step3QuestionResponse;
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
 *  #5 (last)
 * GetReport
 */
@Slf4j
@Component
public class GetReport {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    private final String baseUrl = "https://api.infomining-dev.com/rest_api";

    public GetReport() {
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
     *   Full Report Summary
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     * @param reportType <- type of report(report, question) (required)
     */
    public ResponseEntity<ResultData<List<ReportResponse>>> getSummaryReportInfo(String accessToken,
                                                                                 String reportId, String reportType) {
        log.info("GetReport - getSummaryReportInfo");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);
        params.add("report_type", reportType);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/get_summary_report_info")
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
                new ParameterizedTypeReference<ResultData<List<ReportResponse>>>() {}
        );
    }

    /**
     * @apiNote
     *   Full Report Summary
     *
     * @param accessToken <- Your_Access_Token (required)
     * @param reportId <- identifier of the report (required)
     * @param reportType <- type of report(report, question) (required)
     */
    public ResponseEntity<ResultData<List<ReportResponse>>> getSummaryReportStorageInfo(String accessToken,
                                                                                        String reportId, String reportType) {
        log.info("GetReport - getSummaryReportStorageInfo");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("report_id", reportId);
        params.add("report_type", reportType);

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/report/get_summary_report_storage_info")
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
                new ParameterizedTypeReference<ResultData<List<ReportResponse>>>() {}
        );
    }
}
