package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Integer> {
    public Optional<Donor> findDonorBydonorId(int donorId);
    public Optional<Donor> findDonorBydonorEmailId(String donorEmail);
}
