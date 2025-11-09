package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Integer> {

}
