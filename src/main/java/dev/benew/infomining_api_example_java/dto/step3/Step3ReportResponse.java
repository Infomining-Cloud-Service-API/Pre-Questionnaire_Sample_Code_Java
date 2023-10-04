package dev.benew.infomining_api_example_java.dto.step3;

import lombok.Data;

@Data
public class Step3ReportResponse {
    private String report_id; // Identifier of the report
    private String step3_question_id; // Step3 identifier of the question
    private String step3_follow_up_id; // Step3 Additional Question Identifiers for Questions
    private String step3_selection_id; // Step3 identifier of the selection
    private String step3_input_text; // if Subjective Answer, response content
    private String step3_reg_date; // response date
}
