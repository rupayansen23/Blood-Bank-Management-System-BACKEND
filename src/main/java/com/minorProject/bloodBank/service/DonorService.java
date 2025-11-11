package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.dto.DonorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DonorService {
    public DonorDTO donorSignUpService(Donor donor);
    public List<DonorDTO> getALLDonors();
    public DonorDTO getDonorInfoById(int id);
    public ResponseEntity<String> donorLogin(String userName, String password);
}
