package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Integer> {
    @Query("SELECT b FROM BloodRequest b WHERE b.bloodBank.bloodBankId = :id")
    List<BloodRequest> getBloodRequestByBloodBankId(@Param("id") int id);
}
