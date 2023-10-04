package dev.benew.infomining_api_example_java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.benew.infomining_api_example_java.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InfominingApiExampleJavaApplicationTests {
    @Autowired
    Auth auth;
    @Autowired
    PreparationScenario preparationScenario;
    @Autowired
    Step1Scenario step1Scenario;
    @Autowired
    SymptomSelection symptomSelection;
    @Autowired
    Step2Scenario step2Scenario;
    @Autowired
    Step3Scenario step3Scenario;
    @Autowired
    GetReport getReport;
    ObjectMapper mapper = new ObjectMapper();

    String accessToken = "Your Access Token";
    String reportId = "Your Report Id";

    @Test
    void authTest() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void reportStartTest() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());

//        System.out.println(
//                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
//                        preparationScenario.reportStart(accessToken)
//                )
//        );
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        preparationScenario.saveReportTotal(
                                accessToken,
                                "test user",
                                "M",
                                "22",
                                null,
                                null,
                                "66",
                                null,
                                null)
                )
        );
    }

    @Test
    void step1Test() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        step1Scenario.getQuestions(accessToken, null)
                )
        );
//        System.out.println(
//                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
//                        step1Scenario.question(accessToken, null, "base001")
//                )
//        );
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        step1Scenario.step1History(accessToken, reportId)
                )
        );
    }

    @Test
    void symptomTest() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        preparationScenario.reportStart(accessToken)
                )
        );
    }

    @Test
    void step2Test() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        preparationScenario.reportStart(accessToken)
                )
        );
    }

    @Test
    void step3Test() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        preparationScenario.reportStart(accessToken)
                )
        );
    }

    @Test
    void getReportTest() throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                        preparationScenario.reportStart(accessToken)
                )
        );

    }

}
