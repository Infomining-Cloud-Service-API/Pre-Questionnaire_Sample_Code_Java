package dev.benew.infomining_api_example_java.dto.step1;

import lombok.Data;

import java.util.List;

@Data
public class Step1TotalResponse {
    private Step1QuestionResponse step1_question_info;
    private List<Step1SelectionResponse> step1_selection_list;
}
