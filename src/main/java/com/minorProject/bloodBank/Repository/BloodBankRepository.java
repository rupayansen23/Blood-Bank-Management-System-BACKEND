package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {
    public BloodBank findByAdminID(String adminID);
    public BloodBank findByBloodBankName(String bloodBankName);
}
