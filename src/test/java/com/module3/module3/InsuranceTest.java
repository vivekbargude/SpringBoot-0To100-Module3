package com.module3.module3;

import com.module3.module3.entity.Appointment;
import com.module3.module3.entity.Insurance;
import com.module3.module3.service.AppointmentService;
import com.module3.module3.service.InsuranceService;
import com.module3.module3.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;


    @Test
    public void testInsuranceService() {
        Insurance insurance = Insurance
                .builder()
                .provider("HDFC")
                .policyNumber("ERGO_123")
                .validUntil(LocalDate.of(2030,1,1))
                .createdAt(LocalDateTime.now())
                .build();

        var addinsurance = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(addinsurance);


//        patientService.deletePatient(1L);
        var patient = insuranceService.removeInsuranceFromPatient(1L);
        System.out.println(patient);
    }

    @Test
    public void testAppointments() {
        Appointment appointment = Appointment.builder()
                .reason("Headache")
                .appointmentTime(LocalDateTime.of(2026,3,1,0,0,0))
                .build();

        var updatedAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);

        System.out.println(updatedAppointment);

        //Once patient is deleted appointment related to it also gets deleted due to cascading.ALL
        //First delete all appointments then the patient itself.
        patientService.deletePatient(1L);
    }
}
