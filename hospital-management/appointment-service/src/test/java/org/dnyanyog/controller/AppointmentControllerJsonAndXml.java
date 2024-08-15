package org.dnyanyog.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dnyanyog.AppointmentServiceMain;
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
@ContextConfiguration(classes = AppointmentServiceMain.class)
public class AppointmentControllerJsonAndXml {

  @Autowired private MockMvc mockMvc;

  // JSON test cases..

  @Test
  @Order(1)
  public void verifyDirectoryOperationForCaseSuccess() throws Exception {

    String requestBody =
        "{\n"
            + "  \"appointmentId\": \"11\",\n"
            + "  \"patientNameEnglish\": \"kirti\",\n"
            + "  \"appointment_time\": \"15:00\",\n"
            + "  \"examination_date\": \"2024-05-07\",\n"
            + "  \"patientId\": \"33\"\n"
            + "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/appointment/add")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Appointment added successfully!"))
        .andReturn();
  }

  @Test
  @Order(2)
  public void verifyAppointmentServiceForUpdate() throws Exception {

    String appointment_id = "APT63757302";
    String requestBody =
        "{\n"
            + "  \"appointmentId\": \"1\",\n"
            + "  \"patientNameEnglish\": \"shitija\",\n"
            + "  \"appointment_time\": \"13:00\",\n"
            + "  \"examination_date\": \"2024-05-01\",\n"
            + "  \"patientId\": \"3\"\n"
            + "}";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/appointment/" + appointment_id)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Appointment updated successfully!"))
        .andReturn();
  }

  @Test
  @Order(3)
  public void verifyAppointmentServiceForSearchAppointmentId() throws Exception {

    String appointment_id = "APT63757302";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/appointment/" + appointment_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Appointment found successfully!"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyAppointmentServiceForSearchPatientId() throws Exception {

    String patient_id = "PATB50F25C9";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/appointment/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Appointment found successfully!"))
        .andReturn();
  }

  @Test
  @Order(4)
  public void verifyAppointmentServiceForDelete() throws Exception {

    String apointment_id = "APT63757302";

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/appointment/" + apointment_id)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Success"))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.message").value("Appointment deleted successfully !"))
        .andReturn();
  }

  // XML test case

  @Test
  @Order(9)
  public void verifyCaseServiceforAddCase() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/appointment/add")
            .content(
                "<request>\r\n"
                    + "  <appointmentId>3</appointmentId>\r\n"
                    + "  <patientNameEnglish>mayuresh</patientNameEnglish>\r\n"
                    + "  <appointment_time>3.00 am</appointment_time>\r\n"
                    + "  <patientId>22</patientId>\r\n"
                    + "  <examination_date>2024-04-01</examination_date>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Appointment added successfully!"))
        .andReturn();
  }

  @Test
  @Order(9)
  public void verifyCaseServiceforUpdateSuccess() throws Exception {

    String appointment_id = "APTB665BE8D";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/api/v1/appointment/" + appointment_id)
            .content(
                "<request>\r\n"
                    + "  <appointmentId>33</appointmentId>\r\n"
                    + "  <patientNameEnglish>mayuresh</patientNameEnglish>\r\n"
                    + "  <appointment_time>1.00 am</appointment_time>\r\n"
                    + "  <patientId>2</patientId>\r\n"
                    + "  <examination_date>2024-04-02</examination_date>\r\n"
                    + "</request>")
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Appointment updated successfully!"))
        .andReturn();
  }

  @Test
  @Order(9)
  public void verifyCaseServiceforSearchSuccess() throws Exception {

    String appointment_id = "APTB665BE8D";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/appointment/" + appointment_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Appointment found successfully!"))
        .andReturn();
  }

  @Test
  @Order(9)
  public void verifyCaseServiceforSearchSuccessPatientId() throws Exception {

    String patient_id = "PATCA1CB822";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/appointment/patient/" + patient_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Appointment found successfully!"))
        .andReturn();
  }

  @Test
  @Order(9)
  public void verifyCaseServiceforDeleteSuccess() throws Exception {

    String appointment_id = "APTB665BE8D";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete("/api/v1/appointment/" + appointment_id)
            .contentType(MediaType.APPLICATION_XML_VALUE)
            .accept(MediaType.APPLICATION_XML_VALUE);

    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.xpath("/response/status").string("Success"))
        .andExpect(
            MockMvcResultMatchers.xpath("/response/message")
                .string("Appointment deleted successfully !"))
        .andReturn();
  }
}
