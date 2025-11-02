package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
