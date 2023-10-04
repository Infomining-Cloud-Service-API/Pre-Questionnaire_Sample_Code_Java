package dev.benew.infomining_api_example_java.dto.step3;

import lombok.Data;

@Data
public class Step3FollowUpResponse {
    private String step3_follow_up_id; // Step3 Additional Question Identifiers for Questions
    private String step3_follow_up_content; // Step3 Additional Question content for Questions
    private String step3_follow_up_guide; // Step3 Additional Question guide for Questions
    private String step3_follow_up_report_type; // Step3 Additional Question report type for Questions
    private String step3_follow_up_answer_type; // // Step3 Additional Question answer type for Questions (follow up is only Subjective)
}
