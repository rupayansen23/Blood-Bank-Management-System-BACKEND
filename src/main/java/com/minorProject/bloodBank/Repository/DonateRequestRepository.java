package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.DonateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DonateRequestRepository extends JpaRepository<DonateRequest, Integer> {
    List<DonateRequest> findByBloodBank_BloodBankId(int bloodBankId);
    List<DonateRequest> findByDonor_DonorId(int donorId);
    Optional<DonateRequest> findByReqId(int reqId);
    @Query("""
        SELECT COUNT(DISTINCT dr.donor)
        FROM DonateRequest dr
        WHERE dr.bloodBank.id = :bloodBankId
    """)
    int countDistinctDonorsByBloodBankId(@Param("bloodBankId") int bloodBankId);

}
