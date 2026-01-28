package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.HospitalBloodGroup;
import com.minorProject.bloodBank.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalBloodGroupRepo extends JpaRepository<HospitalBloodGroup, Integer> {
    HospitalBloodGroup findByHospitalBloodGroupType(BloodGroup hospitalBloodGroupType);
}
