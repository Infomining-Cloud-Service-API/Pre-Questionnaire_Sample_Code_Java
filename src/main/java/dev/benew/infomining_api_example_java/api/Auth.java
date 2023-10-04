package dev.benew.infomining_api_example_java.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.dto.AuthTokenResponse;
import dev.benew.infomining_api_example_java.dto.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * Authentication API Example
 */
@Slf4j
@Component
public class Auth {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();
    private final String baseUrl = "https://auth.infomining-cloud.com";

    public Auth() {
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
     *   getToken
     *
     * @param projectId     <- Your_Project_Id (required)
     * @param projectSecret <- Your_Project_Secret (required)
     */
    public ResponseEntity<ResultData<AuthTokenResponse>> getToken(String projectId, String projectSecret) {
        log.info("Auth - getToken");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/auth/token")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Project-Id", projectId);
        headers.set("Project-Secret", projectSecret);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<ResultData<AuthTokenResponse>>() {}
        );
    }

    /**
     * @apiNote
     *   getTokenByRefreshToken
     *
     * @param refreshToken <- Your_Refresh_Token (required)
     */
    public ResponseEntity<ResultData<AuthTokenResponse>> getTokenByRefreshToken(String refreshToken) {
        log.info("Auth - getTokenByRefreshToken");

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/v1/auth/refresh_token")
                .encode().build().toUri();
        log.info("uri:" + uri);

        /** parameter, header **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Refresh-Token", refreshToken);

        return restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<ResultData<AuthTokenResponse>>() {}
        );
    }
}
