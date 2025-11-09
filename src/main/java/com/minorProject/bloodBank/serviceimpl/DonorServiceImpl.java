package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.Repository.DonorRepository;
import com.minorProject.bloodBank.dto.DonorDTO;
import com.minorProject.bloodBank.service.DonorService;
import com.minorProject.bloodBank.utils.DonorConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
