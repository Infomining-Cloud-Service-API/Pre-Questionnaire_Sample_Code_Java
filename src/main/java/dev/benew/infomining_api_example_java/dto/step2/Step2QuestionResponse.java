package dev.benew.infomining_api_example_java.dto.step2;

import lombok.Data;

import java.util.List;

@Data
public class Step2QuestionResponse {
    private String step2_question_id; // Step 2 identifier of the question
    private String step2_question_content; // Step 2 content of the question
    private String step2_guide; // Step 2 guide of the question
    private String step2_report_type; // Step 2 Reporting Category for Questions
    private String step2_answer_type; // QuestionType ex) Subjective, Objective
    private String step2_max_selection_count; // Maximum number of choices for optional questions
    private String step2_branch_bool; // Whether of scenario questions

    private List<String> step2_img_array; // Step2 Sample image list when image type question
}
