package dev.benew.infomining_api_example_java.dto.step2;

import lombok.Data;

@Data
public class Step2ReportResponse {
    private String report_id; // Identifier of the report
    private String step2_question_id; // Step 2 identifier of the question
    private String step2_selection_id; // Step 2 identifier of the selection
    private String step2_input_text; // if Subjective Answer, response content
    private String step2_branch_bool; // Whether of scenario questions
    private String step2_reg_date; // response date
}
