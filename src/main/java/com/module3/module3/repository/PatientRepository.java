package com.module3.module3.repository;

import com.module3.module3.dto.BloodGroupStats;
import com.module3.module3.dto.CPatientInfo;
import com.module3.module3.dto.IPatientInfo;
import com.module3.module3.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByEmailContaining(String d);

    /// Projection using Interface having getter methods.
    @Query("""
            select p.id as id,p.name as name ,p.email as email from Patient p
            """)
    List<IPatientInfo> getPatientsInfo();



    /// Projection using concrete DTO classes.
    @Query("""
            select new com.module3.module3.dto.CPatientInfo(p.id ,p.name) from Patient p
            """)
    List<CPatientInfo> getAllPatientsInfoConcrete();



    /// Projection of aggregate queries resulting in field which is not present for e.g. COUNT, AVG.
    /// new com.module3.module3.dto.BloodGroupStats(p.bloodGroup, COUNT(p)) -> creates a new object using constructor of our own dto.
    @Query("""
            select new com.module3.module3.dto.BloodGroupStats(p.bloodGroup, COUNT(p)) from Patient p group by p.bloodGroup order by COUNT(p)
            """)
    List<BloodGroupStats> getBloodGroupStats();


    /// Update in DB
    /// Take extra care while executing these modifying queries such as @Transactional and @Modifying

    @Transactional
    @Modifying
    @Query("""
            UPDATE Patient p set p.name = :name where p.id = :id
            """)
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);

}
