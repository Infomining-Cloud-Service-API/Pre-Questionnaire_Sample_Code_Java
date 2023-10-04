package dev.benew.infomining_api_example_java.dto.step1;

import lombok.Data;

@Data
public class Step1ReportResponse {
    private String report_id; // Identifier of the report
    private String step1_question_id; // Step2 identifier of the question
    private String step1_selection_id; // Step2 identifier of the selection
    private String step1_input_text; // if Subjective Answer, response content
    private String step1_reg_date; // response date
}
