package dev.benew.infomining_api_example_java.dto.step3;

import lombok.Data;

@Data
public class Step3QuestionResponse {
    private String step3_question_id; // Step 3 identifier of the question
    private String step3_question_content; // Step 3 content of the question
    private String step3_guide; // Step 3 guide of the question
    private String step3_report_type; // Step 3 Reporting Category for Questions
    private String step3_answer_type; // QuestionType ex) Subjective, Objective
    private String step3_max_selection_count; // Maximum number of choices for optional questions
    private String step3_follow_up_id; // Step3 Additional Question Identifiers for Questions
}
