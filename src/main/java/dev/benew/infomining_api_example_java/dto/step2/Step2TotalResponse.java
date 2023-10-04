package dev.benew.infomining_api_example_java.dto.step2;

import lombok.Data;

import java.util.List;

@Data
public class Step2TotalResponse {
    private Step2QuestionResponse step2_question_info;
    private List<Step2SelectionResponse> step2_selection_list;
}
