package dev.benew.infomining_api_example_java.dto;

import lombok.Data;

@Data
public class ResultData<e> {
    private Integer http_status;
    private String code;
    private e data;
    private String message;
}
