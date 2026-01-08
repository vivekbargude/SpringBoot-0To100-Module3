package com.module3.module3.service;

import com.module3.module3.entity.Insurance;
import com.module3.module3.entity.Patient;
import com.module3.module3.repository.InsuranceRepository;
import com.module3.module3.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId) {


        Patient patient = patientRepository.findById(patientId).orElseThrow();

        //This is because of cascading that persists the both patient and insurance entity.
        patient.setInsurance(insurance); //Dirty write -> create a new insurance instance and links it with the patient.

        insurance.setPatient(patient); //Optional -> so that insurance can have access to patient to maintain bidirectional flow.

        return insurance;
    }



}
