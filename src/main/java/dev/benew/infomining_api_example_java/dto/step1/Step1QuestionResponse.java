package dev.benew.infomining_api_example_java.dto.step1;

import lombok.Data;

import java.util.List;

@Data
public class Step1QuestionResponse {
    private String step1_question_id; // Step 1 identifier of the question
    private String step1_question_content; // Step 1 content of the question
    private String step1_guide; // Step 1 guide of the question
    private String step1_report_type; // Step 1 Reporting Category for Questions
    private String step1_answer_type; // QuestionType ex) Subjective, Objective
    private String step1_max_selection_count; // Maximum number of choices for optional questions
}
