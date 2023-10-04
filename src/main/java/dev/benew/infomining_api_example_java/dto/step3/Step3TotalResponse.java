package dev.benew.infomining_api_example_java.dto.step3;

import lombok.Data;

import java.util.List;

@Data
public class Step3TotalResponse {
    private Step3QuestionResponse step3_question_info;
    private List<Step3SelectionResponse> step3_selection_list;
}
