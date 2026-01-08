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

    /*
    Orphan removal means when the parents gets removed the child becomes orphan.
    for e.g the patient takes a new insurance and hence the old one becomes orphan and hence either can say
    the patient no longer has the insurance or update the insurance.
     */
    @Transactional
    public Insurance updateInsuranceOfPatient(Insurance insurance, Long patientId) {

         /*
        orphanRemoval = true tells that when parent child relationship gets disassociated get rid of the child also from the DB.
         */


        Patient patient = patientRepository.findById(patientId).orElseThrow();

        // Previous insurance gets deleted and new insurance objects gets created and gets linked to patient.

        patient.setInsurance(insurance); //Dirty write -> create a new insurance instance and links it with the patient.

        insurance.setPatient(patient); //Optional -> so that insurance can have access to patient to maintain bidirectional flow.

        return insurance;
    }


    @Transactional
    public Patient removeInsuranceFromPatient(Long patientId) {

        /*
        orphanRemoval = true tells that when parent child relationship gets disassociated get rid of the child also from the DB.
         */


        Patient patient = patientRepository.findById(patientId).orElseThrow();

        //This is because of cascading that persists the both patient and insurance entity.
        patient.setInsurance(null); //Dirty write -> create a new insurance instance and links it with the patient.

        return patient;
    }


}
