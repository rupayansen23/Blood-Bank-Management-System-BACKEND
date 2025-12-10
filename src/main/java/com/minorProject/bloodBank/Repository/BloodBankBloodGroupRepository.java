package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.BloodBankBloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodBankBloodGroupRepository extends JpaRepository<BloodBankBloodGroup, Integer> {
    BloodBankBloodGroup findByBloodBankBloodGroupType(String bloodBankBloodGroupType);
}
