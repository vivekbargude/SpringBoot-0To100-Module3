package com.module3.module3;

import com.module3.module3.dto.BloodGroupStats;
import com.module3.module3.dto.CPatientInfo;
import com.module3.module3.dto.IPatientInfo;
import com.module3.module3.entity.Patient;
import com.module3.module3.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatient() {
//        List<Patient> patientList = patientRepository.findAll();
//        List<CPatientInfo> patientList = patientRepository.getAllPatientsInfoConcrete();
//        List<BloodGroupStats> bloodGroupStats = patientRepository.getBloodGroupStats();
//
//        for(BloodGroupStats b : bloodGroupStats) {
//            System.out.println(b);
//        }

        System.out.println(patientRepository.updatePatientNameWithId("Vivek Bargude", 1L));

        System.out.println(patientRepository.findById(1L));
    }

}
