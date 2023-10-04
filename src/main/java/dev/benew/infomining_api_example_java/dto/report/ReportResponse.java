package dev.benew.infomining_api_example_java.dto.report;

import lombok.Data;

@Data
public class ReportResponse {
    private String step_idx;
    private String step_type;
    private String report_category;
    private String report_type;
    private String report_question;
    private String report_text;
}
