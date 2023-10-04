package dev.benew.infomining_api_example_java.dto.step3;

import lombok.Data;

@Data
public class Step3SelectionResponse {
    private String step3_selection_id; // Step3 identifier of the selection
    private String step3_selection_is_follow_up; // status (Step3 Should I go to follow_up when the option is selected)
    private String step3_selection_content; // Step3 content of the selection
}
