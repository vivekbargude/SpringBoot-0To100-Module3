package com.module3.module3.service;

import com.module3.module3.entity.Appointment;
import com.module3.module3.entity.Doctor;
import com.module3.module3.entity.Patient;
import com.module3.module3.repository.AppointmentRepository;
import com.module3.module3.repository.DoctorRepository;
import com.module3.module3.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;



    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);


        return appointment;
    }

    @Transactional
    public void deleteAppointment(Long appointmentId){}

}
