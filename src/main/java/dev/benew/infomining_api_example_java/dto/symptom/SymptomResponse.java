package dev.benew.infomining_api_example_java.dto.symptom;

import lombok.Data;

@Data
public class SymptomResponse {
    private String symptom_id; // symptom identifier
    private String symptom_content; // symptom content
}
