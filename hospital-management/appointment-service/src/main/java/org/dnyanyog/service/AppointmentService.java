package org.dnyanyog.service;

import org.dnyanyog.dto.AppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;

import jakarta.validation.Valid;

public interface AppointmentService {
  public AppointmentResponse addAppointment(@Valid AppointmentRequest request) throws Exception;

  public AppointmentResponse updateAppointment(String appointment_id, AppointmentRequest request);

  public AppointmentResponse getSingleAppointment(String patient_id);

  public AppointmentResponse getAppointment(String appointment_id);

  public AppointmentResponse deleteAppointment(String appointment_id);
}
