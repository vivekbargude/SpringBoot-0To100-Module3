package com.module3.module3.service;

import com.module3.module3.entity.Patient;
import com.module3.module3.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*

        Patient patient1 = patientRepository.findById(1L).orElseThrow();
        Patient patient2 = patientRepository.findById(1L).orElseThrow();

        System.out.println(patient1 + "" + patient2);
        System.out.println(patient1==patient2);

1. Two database calls are made separate for the same query in different persistent context when it is not in transactional context. :



O/p : Hibernate: select p1_0.id,p1_0.birth_date,p1_0.blood_group,p1_0.created_at,p1_0.email,p1_0.gender,p1_0.name from patient p1_0 where p1_0.id=?
Hibernate: select p1_0.id,p1_0.birth_date,p1_0.blood_group,p1_0.created_at,p1_0.email,p1_0.gender,p1_0.name from patient p1_0 where p1_0.id=?
Patient(id=1, name=Aarav Sharma, birthDate=1990-05-10T00:00, email=aarav.sharma@example.com, gender=MALE, bloodGroup=O_POSITIVE, createdAt=null)Patient(id=1, name=Aarav Sharma, birthDate=1990-05-10T00:00, email=aarav.sharma@example.com, gender=MALE, bloodGroup=O_POSITIVE, createdAt=null)
false


2. After adding @Transactional annotation DB will start a transaction and all query inside it will run in a transactional context.

When in transactional then all queries are executed in same persistent context so for first fetch DB call is made but for the next fetch data
is retrived from the persistent context only.


O/P: Hibernate: select p1_0.id,p1_0.birth_date,p1_0.blood_group,p1_0.created_at,p1_0.email,p1_0.gender,p1_0.name from patient p1_0 where p1_0.id=?
Patient(id=1, name=Aarav Sharma, birthDate=1990-05-10T00:00, email=aarav.sharma@example.com, gender=MALE, bloodGroup=O_POSITIVE, createdAt=null)Patient(id=1, name=Aarav Sharma, birthDate=1990-05-10T00:00, email=aarav.sharma@example.com, gender=MALE, bloodGroup=O_POSITIVE, createdAt=null)
true

After adding transactional context all results of the queries are saved in the persistent context for further fetch hence it works as a cache.

 */

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    @Transactional
    public void testPatientTransaction() {
        Patient patient1 = patientRepository.findById(1L).orElseThrow();
        Patient patient2 = patientRepository.findById(1L).orElseThrow();

        System.out.println(patient1 + "" + patient2);
        System.out.println(patient1==patient2);

        /*
        Any dirty write made to the object will automatically update the object in DB by making the update call to DB -> because of persistent context gets synced with DB.
         */
        patient1.setName("Vivek");
    }

    @Transactional
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patientRepository.deleteById(patientId); //Till this it will delete the patient but not the insurance associated with it.
        //For that we have to set the removal strategy. i.e cascading.all

        //Now if the patient is deleted the insurance associated with it also gets deleted.

    }
}
