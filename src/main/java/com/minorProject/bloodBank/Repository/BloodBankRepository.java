package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {
}
