package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.dto.HospitalDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HospitalService {
    public HospitalDTO saveHospital(Hospital hospital);
    public ResponseEntity<String> hospitalLogin(String userName, String password);
    public List<HospitalDTO> getAllHospitals();
}
