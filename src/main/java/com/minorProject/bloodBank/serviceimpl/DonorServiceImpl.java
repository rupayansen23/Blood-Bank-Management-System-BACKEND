package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.Repository.DonorRepository;
import com.minorProject.bloodBank.dto.DonorDTO;
import com.minorProject.bloodBank.exceptions.ResourceNotFoundException;
import com.minorProject.bloodBank.service.DonorService;
import com.minorProject.bloodBank.utils.DonorConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DonorServiceImpl implements DonorService {

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    DonorConverter donorConverter;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public DonorDTO donorSignUpService(Donor donor) {
        donor.setPassword(passwordEncoder.encode(donor.getPassword()));
        donorRepository.save(donor);
        return donorConverter.convertEntityToDonorDTO(donor);
    }

    @Override
    public List<DonorDTO> getALLDonors() {
        List<Donor> donors = donorRepository.findAll();
        List<DonorDTO> donorDTOS = new ArrayList<>();
        for(Donor donor : donors) {
            donorDTOS.add(donorConverter.convertEntityToDonorDTO(donor));
        }
        return donorDTOS;
    }

    @Override
    public DonorDTO getDonorInfoById(String emailId) {
        Donor donor = donorRepository.findDonorBydonorEmailId(emailId).orElseThrow(
                ()-> new ResourceNotFoundException("Donor", "id", emailId)
        );
        return donorConverter.convertEntityToDonorDTO(donor);
    }

    @Override
    public ResponseEntity<?> donorLogin(String emailId, String password) {
        try {
            Optional<Donor> donorOpt = donorRepository.findDonorBydonorEmailId(emailId);

            if (donorOpt.isPresent()) {
                Donor donor = donorOpt.get();
                if (passwordEncoder.matches(password, donor.getPassword())) {
                    return ResponseEntity.ok(donorConverter.convertEntityToDonorDTO(donor));
                } else {
                    throw new RuntimeException("Invalid Credentials");
                }
            } else {
                // donor not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Donor not found with email: " + emailId);
            }
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re.getMessage());
        }
    }

}
