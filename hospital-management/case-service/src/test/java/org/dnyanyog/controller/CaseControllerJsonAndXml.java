package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.CaseServiceMain;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CaseServiceMain.class)
public class CaseControllerJsonAndXml {

  @Autowired private MockMvc mockMvc;

  // JSON test cases..
  @Test
  @Order(1)
  public void verifyDirectoryOperationForCaseSuccess() throws Exception {

    String requestBody =
        "{\n"
            + "  \"patientId\": \"11\",\n"
            + "  \"patientNameEnglish\": \"kirti\",\n"
            + "  \"case_number\": \"3\",\n"
            + "  \"examination_date\": \"2024-05-07\",\n"
            + "  \"prescription\": \"medicine\",\n"
            + "  \"symptoms\": \"fever\"\n"
            + "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/add")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case added successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("3"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("kirti"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-05-07"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("medicine"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("fever"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verfiyCaseServiceUpdateCase() throws Exception {
    String case_id = "CASDBB8DABE";

    String requestBody =
        "{\n"
            + "  \"patientId\": \"12\",\n"
            + "  \"patientNameEnglish\": \"samruddhi\",\n"
            + "  \"case_number\": \"2\",\n"
            + "  \"examination_date\": \"2024-05-01\",\n"
            + "  \"prescription\": \"paracitemol\",\n"
            + "  \"symptoms\": \"temperature\"\n"
            + "}";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/" + case_id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case updated successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("2"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("samruddhi"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-05-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("paracitemol"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("temperature"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyCaseServiceForSearch() throws Exception {
    String case_id = "CASDBB8DABE";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/patient/" + case_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case found successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("2"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("kirti"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-05-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("paracitemol"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("temperature"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyCaseServiceForSearchPatientId() throws Exception {
    String patient_id = "PAT4B179ACA";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/" + patient_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case found successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.case_number").value("2"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("kirti"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.examination_date").value("2024-05-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prescription").value("paracitemol"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.symptoms").value("temperature"))
        .andReturn();
  }

  @Test
  @Order(5)
  public void verifyCaseServiceDelete() throws Exception {

    String case_id = "CASDBB8DABE";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/case/" + case_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Case deleted successfully !"))
        .andReturn();
  }

  // XML test cases..

  @Test
  @Order(9)
  public void verifyCaseServiceforAddCase() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/add")
            .content(
                "<request>\r\n"
                    + "  <patientId>33</patientId>\r\n"
                    + "  <patientNameEnglish>ravina</patientNameEnglish>\r\n"
                    + "  <case_number>32</case_number>\r\n"
                    + "  <prescription>omini</prescription>\r\n"
                    + "  <symptoms>omit</symptoms>\r\n"
                    + "  <examination_date>2024-04-01</examination_date>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case added successfully!"))
        .andReturn();
  }

  @Test
  @Order(10)
  public void verifyCaseServiceforUpdateCaseSuccess() throws Exception {

    String case_id = "CASFEC26C87";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/case/" + case_id)
            .content(
                "<request>\r\n"
                    + "  <patientId>22</patientId>\r\n"
                    + "  <patientNameEnglish>trisha</patientNameEnglish>\r\n"
                    + "  <case_number>3</case_number>\r\n"
                    + "  <prescription>bp_tab</prescription>\r\n"
                    + "  <symptoms>high blood presure</symptoms>\r\n"
                    + "  <examination_date>2024-04-10</examination_date>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string("22"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("trisha"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("3"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("bp_tab"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("high blood presure"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-04-10"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case updated successfully!"))
        .andReturn();
  }

  @Test
  @Order(11)
  public void verifyCaseServiceforSearchCaseCaseId() throws Exception {

    String case_id = "CASFEC26C87";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/patient/" + case_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string("22"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ravina"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("3"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("bp_tab"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("high blood presure"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-04-10"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case found successfully!"))
        .andReturn();
  }

  @Test
  @Order(12)
  public void verifyCaseServiceforSearchCasePatientId() throws Exception {

    String patient_id = "PAT2CDE5022";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/case/" + patient_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string("22"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ravina"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("3"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("bp_tab"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("high blood presure"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-04-10"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case found successfully!"))
        .andReturn();
  }

  @Test
  @Order(13)
  public void verifyCaseServiceforDeleteCase() throws Exception {

    String case_id = "CASFEC26C87";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/case/" + case_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientId").string("22"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ravina"))
        .andExpect(MockMvcResultMatchers.xpath("/response/case_number").string("3"))
        .andExpect(MockMvcResultMatchers.xpath("/response/prescription").string("bp_tab"))
        .andExpect(MockMvcResultMatchers.xpath("/response/symptoms").string("high blood presure"))
        .andExpect(MockMvcResultMatchers.xpath("/response/examination_date").string("2024-04-10"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Case deleted successfully !"))
        .andReturn();
  }
}
