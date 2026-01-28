package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.BloodBankBloodGroup;
import com.minorProject.bloodBank.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BloodBankBloodGroupRepository extends JpaRepository<BloodBankBloodGroup, Integer> {
    @Query("""
        SELECT bbg
        FROM BloodBankBloodGroup bbg
        WHERE bbg.bloodBank.bloodBankId = :bankId
          AND bbg.bloodBankBloodGroupType = :group
    """)
    Optional<BloodBankBloodGroup> findByBankAndGroup(
            @Param("bankId") Integer bankId,
            @Param("group") BloodGroup bloodGroup
    );
}
