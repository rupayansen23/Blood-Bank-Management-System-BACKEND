package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.Repository.HospitalRepository;
import com.minorProject.bloodBank.dto.HospitalDTO;
import com.minorProject.bloodBank.service.HospitalService;
import com.minorProject.bloodBank.utils.HospitalConverter;
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
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    HospitalConverter hospitalConverter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public HospitalDTO saveHospital(Hospital hospital) {
        hospital.setPassword(passwordEncoder.encode(hospital.getPassword()));
        hospitalRepository.save(hospital);
        HospitalDTO hospitalDTO = hospitalConverter.convertEntitytoHospitalDTO(hospital);
        return hospitalDTO;
    }

    @Override
    public ResponseEntity<?> hospitalLogin(String userName, String password) {
        try {
            Hospital hospital = hospitalRepository.findByAdminID(userName);
            if(hospital != null && passwordEncoder.matches(password, hospital.getPassword())) {
                return ResponseEntity.ok(hospitalConverter.convertEntitytoHospitalDTO(hospital));
            } else {
                throw new RuntimeException("Login Failed");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<Hospital> allHospitals = hospitalRepository.findAll();
        List<HospitalDTO> allHospitalDTOs = new ArrayList<>();
        for(Hospital hp : allHospitals) {
            allHospitalDTOs.add(hospitalConverter.convertEntitytoHospitalDTO(hp));
        }
        return allHospitalDTOs;
    }
}
