package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.dto.DonorDTO;

import java.util.List;

public interface DonorService {
    public DonorDTO donorSignUpService(Donor donor);
    public List<DonorDTO> getALLDonors();
}
