package appointment_delete;

import appointment_page.AppointmentPageScreen;
import case_page.CasePageScreen;
import common.RestUtil;
import dashboard_page.DashboardScreen;
import dto.AppointmentResponse;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login_screen.LoginScreen;
import patient_page.PatientScreen;
import user_page.UserScreen;

public class DeleteAppointmentScreenController {
  @FXML private Button patients;

  @FXML private Button cases;

  @FXML private Button appointments;

  @FXML private Button users;

  @FXML private Button logout;

  @FXML private Label userMessage;

  @FXML private Button dashboard;

  @FXML private TextField patient_name_english;

  @FXML private TextField patient_id;

  @FXML private TextField appointment_id;

  @FXML private DatePicker examination_date;

  @FXML private TextField appointment_time;

  @FXML private Button cancle;

  @FXML private Button delete;

  @FXML private TextField patient_id_search;

  @FXML private TextField appointment_id_search;

  @FXML private Button search;

  public void PatientsButton(ActionEvent event) throws IOException {

    try {
      PatientScreen.showPatientScreen();
    } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void CasesButton(ActionEvent event) throws IOException {
    try {
      CasePageScreen.showCasePageScreen();
    } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void AppointmentButton(ActionEvent event) throws IOException {
    try {
      AppointmentPageScreen.showAppointmentPageScreen();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void UsersButton(ActionEvent event) throws IOException {
    try {
      UserScreen.showUserScreen();
    } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void LogoutButton(ActionEvent event) throws IOException {

    try {
      LoginScreen.showLoginScreen();
    } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void Cancle_Button(ActionEvent event) {
    try {
      DashboardScreen.showDashboardScreen();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void Dashboard(ActionEvent event) throws IOException {
    try {
      DashboardScreen.showDashboardScreen();
    } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void Search_Button(ActionEvent event) throws IOException {
    String appointmentId = appointment_id_search.getText().trim();
    String patientId = patient_id_search.getText().trim();

    AppointmentResponse response = null;
    try {
      if (!patientId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8085/api/v1/appointment/patient/" + patientId,
                AppointmentResponse.class);
      } else if (!appointmentId.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8085/api/v1/appointment/" + appointmentId,
                AppointmentResponse.class);
      }
      if (response != null && response.getStatus().equals("Success")) {
        patient_name_english.setText(response.getPatientNameEnglish());
        patient_id.setText(response.getPatientId());
        appointment_id.setText(response.getAppointmentId());
        examination_date.setValue(LocalDate.parse(response.getExamination_date()));
        appointment_time.setText(response.getAppointment_time());
        setFieldsEditable(false);

      } else {
        userMessage.setText("Error, Please enter valid patient Id or appointment Id");
      }
    } catch (Exception e) {
      e.printStackTrace();
      userMessage.setText("Error, An error occurred while fetching the appointment details.");
    }
  }

  public void Delete_Button(ActionEvent event) throws IOException {
    String appointmentId = appointment_id_search.getText().trim();

    if (appointmentId.isEmpty()) {
      userMessage.setText("Error, Please enter a appointment Id to delete.");
      return;
    }

    try {
      AppointmentResponse response =
          RestUtil.sendDeleteRequest(
              "http://localhost:8085/api/v1/appointment/" + appointmentId,
              AppointmentResponse.class);

      if (response != null && response.getStatus().equals("Success")) {
        userMessage.setText("Success, Appointment has been deleted successfully.");
        patient_id_search.clear();
        appointment_id.clear();
        patient_name_english.clear();
        patient_id.clear();
        appointment_time.clear();
        examination_date.setValue(null);
        appointment_id_search.clear();

      } else {
        userMessage.setText(
            "!!Error!! Case not found. Please enter a valid patient ID or case ID.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      userMessage.setText("!!Error!! Case not found. Please enter a valid patient ID or case ID.");
    }
  }

  private void setFieldsEditable(boolean editable) {
    patient_name_english.setEditable(editable);
    patient_id.setEditable(editable);
    appointment_id.setEditable(editable);
    examination_date.setDisable(!editable);
    appointment_time.setEditable(editable);
  }
}
