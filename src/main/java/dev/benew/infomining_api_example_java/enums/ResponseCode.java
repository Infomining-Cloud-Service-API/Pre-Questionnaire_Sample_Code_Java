package dev.benew.infomining_api_example_java.enums;

import org.springframework.http.HttpStatus;

/**
 * code는
 * HttpStatus 코드 베이스로 작성
 * badRequest인 경우 400, 4000
 */
public enum ResponseCode {
    // NOTE: parameter 오류
    PARAMETER_REQUIRED(HttpStatus.BAD_REQUEST, "4000", "No required parameters"),
    // NOTE: 인증 오류
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "4010", "Check your Token or Key"),
    // NOTE: 토큰 만료됬을 때
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "4011", "Expired token"),
    // NOTE: 토큰 형식 안맞을 때 // TODO: 2023-07-21 BAD_REQUEST OR UNAUTHORIZED 뭐 써야할지 검증
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "4012", "Invalid token format"),
    // NOTE: connect key 안맞을 때
    KEY_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "4050", "Key Not Allowed"),

    // NOTE: 인증 과도하게 불렀을 경우
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "4290", "Too many requests"),
    // NOTE: 프로젝트 생성 개수 초과 // TODO: 2023-07-21 뭐 써야할지 검증
    PROJECT_COUNT_OVER(HttpStatus.TOO_MANY_REQUESTS, "4291", "Number of project creation exceeded"),

    // NOTE: 성공
    OK(HttpStatus.OK, "2000", "Success"),
    // NOTE: insert 관련 성공 시
    CREATED(HttpStatus.CREATED, "2010", "Success"),
    // NOTE: 결과 없을 경우
    NO_CONTENT(HttpStatus.OK, "2001", "No Content"),

    // NOTE: 프로젝트 이름 중복 // TODO: 2023-07-21 뭐 써야할지 검증
    PROJECT_NAME_DUPLICATE(HttpStatus.CONFLICT, "4090", "Duplicate project name"),

    // DEPRECATED: 2023-09-05, 화, 16:27   안쓰임
    /*TOKEN_FORMAT_WRONG(HttpStatus.UNAUTHORIZED, "1000", "Invalid token format"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "1001", "Invalid token"),
    ENDPOINT_AUTH(HttpStatus.FORBIDDEN, "1005", "No access for endpoint"),

    LANGUAGE_FORMAT(HttpStatus.BAD_REQUEST, "2002", "Wrong language type format"),
    SAVE_REPORT_FAIL(HttpStatus.BAD_REQUEST, "3001", "Failed to save report"),
    STEP1_GENDER_FAIL(HttpStatus.BAD_REQUEST, "3002", "Wrong gender type format ex)M, F"),
    STEP1_AGE_FAIL(HttpStatus.BAD_REQUEST, "3003", "Wrong age type format"),
    PROFILE_FORMAT(HttpStatus.BAD_REQUEST, "3004", "Wrong profile format"),
    REPORT_ID_WRONG(HttpStatus.BAD_REQUEST, "3005", "Wrong report id"),
    MISSING_PRJ(HttpStatus.BAD_REQUEST, "4001", "Project does not exist"),*/

    // NOTE: Api inactive 상태
    API_INACTIVE(HttpStatus.FORBIDDEN, "4070", "Api Inactive"),
    // NOTE: Api inactive 상태
    API_DELETED(HttpStatus.FORBIDDEN, "4071", "Api Deleted"),
    // NOTE: Api inactive 상태
    PROJECT_INACTIVE(HttpStatus.FORBIDDEN, "4072", "Project Inactive"),

    // NOTE: sever 에러
    ERROR_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "Error"),
    // NOTE: refresh token 발급 에러
    ERROR_REFRESH(HttpStatus.INTERNAL_SERVER_ERROR, "5001", "Error"),
    // NOTE: access token 교환(resource 서버 통신) api 실패
    ERROR_ACCESS_CHANGE(HttpStatus.INTERNAL_SERVER_ERROR, "5002", "Error"),
    // NOTE: 토큰 교체 작업 (db)
    ERROR_TOKEN_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "5003", "Error"),
    // NOTE: 프로젝트 생성 에러
    ERROR_PROJECT_CREATE(HttpStatus.INTERNAL_SERVER_ERROR, "5004", "Error"),
    // NOTE: Connect_key 에러
    ERROR_CONNECT_KEY(HttpStatus.INTERNAL_SERVER_ERROR, "5005", "Error"),
    // NOTE: Database 에러
    ERROR_DATABASE(HttpStatus.INTERNAL_SERVER_ERROR, "5006", "Error");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ResponseCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
