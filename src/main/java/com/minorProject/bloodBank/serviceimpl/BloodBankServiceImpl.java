package com.minorProject.bloodBank.serviceimpl;


import com.minorProject.bloodBank.Entity.BloodBank;
import com.minorProject.bloodBank.Repository.BloodBankRepository;
import com.minorProject.bloodBank.dto.BloodBankDTO;
import com.minorProject.bloodBank.exceptions.ResourceNotFoundException;
import com.minorProject.bloodBank.security.SecurityConfig;
import com.minorProject.bloodBank.service.BloodBankService;
import com.minorProject.bloodBank.utils.BloodBankConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class BloodBankServiceImpl implements BloodBankService {

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    BloodBankConverter bloodBankConverter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public BloodBankDTO getBloodBankById(int bloodBankId) throws ResourceNotFoundException {
        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).orElseThrow(
                ()-> new ResourceNotFoundException("BloodBank", "Id", bloodBankId)
        );
        return bloodBankConverter.convertEntityToBloodBankDTO(bloodBank);
    }

    @Override
    public BloodBankDTO saveBloodBank(BloodBank bloodBank) {
        bloodBank.setPassword(passwordEncoder.encode(bloodBank.getPassword()));
        bloodBankRepository.save(bloodBank);
        return bloodBankConverter.convertEntityToBloodBankDTO(bloodBank);
    }

    @Override
    public List<BloodBankDTO> getAllBloodBanks() {
        List<BloodBank> allBanks = bloodBankRepository.findAll();
        List<BloodBankDTO> allBankDTOs = new ArrayList<>();
        for(BloodBank b : allBanks) {
            allBankDTOs.add(bloodBankConverter.convertEntityToBloodBankDTO(b));
        }
        return allBankDTOs;
    }

    @Override
    public ResponseEntity<String> bloodBankLogin(String userName, String password) {
        try {
            BloodBank bloodBank = bloodBankRepository.findByAdminID(userName);
            if(bloodBank != null && passwordEncoder.matches(password, bloodBank.getPassword())) {
                return ResponseEntity.ok("Login Success");
            } else {
                throw new RuntimeException("Invalid Credentials");
            }

        } catch (RuntimeException e) {
            //System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
