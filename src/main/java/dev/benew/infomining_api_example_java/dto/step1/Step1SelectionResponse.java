package dev.benew.infomining_api_example_java.dto.step1;

import lombok.Data;

@Data
public class Step1SelectionResponse {
    private String step1_selection_id; // Step1 identifier of the selection
    private String step1_selection_content; // Step1 content of the selection
    private String follow_up_id; // Identifier of the question that follows based on the choice of choice (If null, end of Step1 question)
    private String step1_selection_required; // 0:Not Required    1:Required    2:Depending on your choice
}
