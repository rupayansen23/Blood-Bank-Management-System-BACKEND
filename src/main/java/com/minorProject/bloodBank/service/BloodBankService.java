package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.BloodBank;
import com.minorProject.bloodBank.dto.BloodBankDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BloodBankService {
    public BloodBankDTO saveBloodBank(BloodBank bloodBank);
    public BloodBankDTO getBloodBankById(int bloodBankId);
    public List<BloodBankDTO> getAllBloodBanks();
    public ResponseEntity<String> bloodBankLogin(String userName, String password);
}
