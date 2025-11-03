package com.minorProject.bloodBank.serviceimpl;


import com.minorProject.bloodBank.Entity.BloodBank;
import com.minorProject.bloodBank.Repository.BloodBankRepository;
import com.minorProject.bloodBank.dto.BloodBankDTO;
import com.minorProject.bloodBank.exceptions.ResourceNotFoundException;
import com.minorProject.bloodBank.service.BloodBankService;
import com.minorProject.bloodBank.utils.BloodBankConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BloodBankServiceImpl implements BloodBankService {

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    BloodBankConverter bloodBankConverter;

    @Override
    public BloodBankDTO getBloodBankById(int bloodBankId) throws ResourceNotFoundException {
        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).orElseThrow(
                ()-> new ResourceNotFoundException("BloodBank", "Id", bloodBankId)
        );
        return bloodBankConverter.convertEntityToBloodBankDTO(bloodBank);
    }

    @Override
    public BloodBankDTO saveBloodBank(BloodBank bloodBank) {
        bloodBankRepository.save(bloodBank);
        return bloodBankConverter.convertEntityToBloodBankDTO(bloodBank);
    }
}
