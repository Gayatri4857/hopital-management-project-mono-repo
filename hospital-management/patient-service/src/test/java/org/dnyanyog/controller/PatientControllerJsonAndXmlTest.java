/*package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.PatientApplicationMain;
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
@ContextConfiguration(classes = PatientApplicationMain.class)
public class PatientControllerJsonAndXmlTest {

  @Autowired private MockMvc mockMvc;

  // JSON test cases
  @Test
  @Order(1)
  public void verifyDirectoryOperationForPatientSuccess() throws Exception {

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/add")
            .content(
                "{\n"
                    + "  \"patientNameEnglish\": \"ramesh\",\n"
                    + "  \"patient_name_marathi\": \"फ\",\n"
                    + "  \"birth_date\": \"2006-04-02\",\n"
                    + "  \"gender\": \"female\",\n"
                    + "  \"mobile\": \"9876543241\",\n"
                    + "  \"first_examination_date\": \"2024-05-07\",\n"
                    + "  \"address\": \"pune\"\n"
                    + "}")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Patient added successfully!"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verifyPatientServiceForUpdatePatient() throws Exception {
    String patient_id = "PATE67869D3";

    String requestBody =
        ("{\n"
            + "  \"patientNameEnglish\": \"gauri\",\n"
            + "  \"patient_name_marathi\": \"फ\",\n"
            + "  \"birth_date\": \"2006-04-01\",\n"
            + "  \"gender\": \"male\",\n"
            + "  \"mobile\": \"9876543241\",\n"
            + "  \"first_examination_date\": \"2024-05-01\",\n"
            + "  \"address\": \"mumbai\"\n"
            + "}");

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/" + patient_id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("gauri"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patient_name_marathi").value("फ"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.birth_date").value("2006-04-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("male"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value("9876543241"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.first_examination_date").value("2024-05-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("mumbai"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Patient updated successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verifyPatientServiceForUpdatePatientNotFound() throws Exception {
    String patient_id = "non_exist";

    String requestBody =
        ("{\n"
            + "  \"patientNameEnglish\": \"gauri\",\n"
            + "  \"patient_name_marathi\": \"फ\",\n"
            + "  \"birth_date\": \"2006-04-01\",\n"
            + "  \"gender\": \"male\",\n"
            + "  \"mobile\": \"9876543241\",\n"
            + "  \"first_examination_date\": \"2024-05-01\",\n"
            + "  \"address\": \"mumbai\"\n"
            + "}");

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/" + patient_id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value("Patient not found or invalid request!"))
        .andReturn();
  }

  @Test
  @Order(5)
  public void verifyPatientServiceForSearchPatientSuccess() throws Exception {
    String patient_id = "PATE67869D3";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Patient found successfully!"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patientNameEnglish").value("ramesh"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.patient_name_marathi").value("फ"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.birth_date").value("2006-04-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("male"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value("9876543241"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.first_examination_date").value("2024-05-01"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("mumbai"))
        .andReturn();
  }

  @Test
  @Order(6)
  public void verifyDirectoryServiceForGetSingleUserNotFound() throws Exception {
    String patient_id = "nonexistentUser";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value("Patient not found or invalid request!"))
        .andReturn();
  }

  @Test
  @Order(7)
  public void verifyPatientServiceForDeletePatientSuccess() throws Exception {
    String patient_id = "PATFC237B58";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Patient deleted successfully !"))
        .andReturn();
  }

  @Test
  @Order(8)
  public void verifyPatientServiceForDeletePatientNotFound() throws Exception {
    String patient_id = "nonexistentUser";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Fail"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message")
                .value("Patient not found or invalid request!"))
        .andReturn();
  }

  // XML test cases

  @Test
  @Order(9)
  public void verifyPatientOperationForPatientSuccessUsingXml() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/add")
            .content(
                "<request>\r\n"
                    + "  <patientNameEnglish>ravina</patientNameEnglish>\r\n"
                    + "  <patient_name_marathi>रविण</patient_name_marathi>\r\n"
                    + "  <birth_date>2003-04-01</birth_date>\r\n"
                    + "  <gender>Female</gender>\r\n"
                    + "  <mobile>1234567898</mobile>\r\n"
                    + "  <address>kopargaon</address>\r\n"
                    + "  <first_examination_date>2024-04-01</first_examination_date>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Patient added successfully!"))
        .andReturn();
  }

  @Test
  @Order(10)
  public void verifyPatientServiceForUpdateUsingXml() throws Exception {
    String patient_id = "PAT07DF4F52";

    String requestBody =
        "<request>\r\n"
            + "  <patientNameEnglish>nidhi</patientNameEnglish>\r\n"
            + "  <patient_name_marathi>रविण</patient_name_marathi>\r\n"
            + "  <birth_date>2003-04-02</birth_date>\r\n"
            + "  <gender>Male</gender>\r\n"
            + "  <mobile>1234567899</mobile>\r\n"
            + "  <address>pune</address>\r\n"
            + "  <first_examination_date>2024-04-02</first_examination_date>\r\n"
            + "</request>";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/" + patient_id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("nidhi"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patient_name_marathi").string("रविण"))
        .andExpect(MockMvcResultMatchers.xpath("/response/birth_date").string("2003-04-02"))
        .andExpect(MockMvcResultMatchers.xpath("/response/gender").string("Male"))
        .andExpect(MockMvcResultMatchers.xpath("/response/mobile").string("1234567899"))
        .andExpect(MockMvcResultMatchers.xpath("/response/address").string("pune"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/first_examination_date").string("2024-04-02"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Patient updated successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }

  @Test
  @Order(11)
  public void verifyPatientServiceForUpdateNotUsingXml() throws Exception {
    String patient_id = "non_exist";

    String requestBody =
        "<request>\r\n"
            + "  <patientNameEnglish>nidhi</patientNameEnglish>\r\n"
            + "  <patient_name_marathi>रविण</patient_name_marathi>\r\n"
            + "  <birth_date>2003-04-02</birth_date>\r\n"
            + "  <gender>Male</gender>\r\n"
            + "  <mobile>1234567899</mobile>\r\n"
            + "  <address>pune</address>\r\n"
            + "  <first_examination_date>2024-04-02</first_examination_date>\r\n"
            + "</request>";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/patient/" + patient_id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("$.status").string("Fail"))
        .andExpect(
            MockMvcResultMatchers.xpath("$.message")
                .string("Patient not found or invalid request!"))
        .andReturn();
  }

  @Test
  @Order(12)
  public void verifyPatientServiceForSearchPatientSuccessXml() throws Exception {
    String patient_id = "PAT07DF4F52";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ravina"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patient_name_marathi").string("रविण"))
        .andExpect(MockMvcResultMatchers.xpath("/response/birth_date").string("2003-04-02"))
        .andExpect(MockMvcResultMatchers.xpath("/response/gender").string("Male"))
        .andExpect(MockMvcResultMatchers.xpath("/response/mobile").string("1234567899"))
        .andExpect(MockMvcResultMatchers.xpath("/response/address").string("pune"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/first_examination_date").string("2024-04-02"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Patient found successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }

  @Test
  @Order(13)
  public void verifyPatientServiceForSearchPatientSuccessUsingPatientnameXml() throws Exception {
    String patient_name_english = "ravina";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/patient/name/" + patient_name_english)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/patientNameEnglish").string("ravina"))
        .andExpect(MockMvcResultMatchers.xpath("/response/patient_name_marathi").string("रविण"))
        .andExpect(MockMvcResultMatchers.xpath("/response/birth_date").string("2003-04-02"))
        .andExpect(MockMvcResultMatchers.xpath("/response/gender").string("Male"))
        .andExpect(MockMvcResultMatchers.xpath("/response/mobile").string("1234567899"))
        .andExpect(MockMvcResultMatchers.xpath("/response/address").string("pune"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/first_examination_date").string("2024-04-02"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message").string("Patient found successfully!"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }

  @Test
  @Order(14)
  public void verfiyPatientServiceForDeletePatientSuccessXml() throws Exception {
    String patient_id = "PAT07DF4F52";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Patient deleted successfully !"))
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andReturn();
  }
}
*/