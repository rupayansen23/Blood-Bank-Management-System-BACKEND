package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.BloodBank;
import com.minorProject.bloodBank.dto.BloodBankDTO;

public interface BloodBankService {
    public BloodBankDTO saveBloodBank(BloodBank bloodBank);
    public BloodBankDTO getBloodBankById(int bloodBankId);
}
